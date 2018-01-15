package domain.model.base

import org.scalacheck.Arbitrary

trait IntValue extends Value[Int]

trait IntValueCompanion[A <: IntValue] extends ValueCompanion[Int, A] with (Int => A) {
  override val convert: PartialFunction[Int, A] = {
    case a => apply(a)
  }

  implicit val arbitrary: Arbitrary[A] = Arbitrary(Arbitrary.arbInt.arbitrary.map(apply))
}
