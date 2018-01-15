package domain.model.base

trait StringValue extends Value[String]
import org.scalacheck.{Arbitrary, Gen}

import scala.util.matching.Regex

trait StringValueCompanion[A <: StringValue] extends ValueCompanion[String, A] with (String => A) {
  protected def charsArbitrary(chars: Seq[Char]): Arbitrary[A] = charsArbitrary(chars, 191)

  protected def charsArbitrary(chars: Seq[Char], maxLength: Int): Arbitrary[A] = Arbitrary {
    for {
      size <- Gen.choose(1, maxLength)
      list <- Gen.listOfN(size, Gen.oneOf(chars))
    } yield apply(list.mkString)
  }

  override def convert: PartialFunction[String, A] = {
    case a => apply(a)
  }
}

abstract class RegexValueCompanion[A <: StringValue](regex: Regex) extends StringValueCompanion[A] {
  override def convert: PartialFunction[String, A] = {
    case regex(s) => apply(s)
  }
}
