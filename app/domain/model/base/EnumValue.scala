package domain.model.base

import org.scalacheck.{Arbitrary, Gen}

abstract class EnumValue[A](val value: A) extends Value[A]

trait EnumValueCompanion[A, B <: EnumValue[A]] extends ValueCompanion[A, B] {
  def values: Set[B]

  override val convert: PartialFunction[A, B] = {
    case a if values.exists(_.value == a) => values.find(_.value == a).get
  }

  def apply(a: A): B = convert.lift(a).getOrElse(
    throw new IllegalArgumentException(s"${this.getClass.getSimpleName} doesn't include ${a.toString}"))

  implicit lazy val arbitrary: Arbitrary[B] = Arbitrary(Gen.oneOf(values.toSeq))
}
