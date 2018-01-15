package utils
import org.scalacheck.rng.Seed
import org.scalacheck.{Arbitrary, Gen}
import play.api.libs.json.{JsValue, Json, Writes}

trait ScalaCheckUtils {

  def sample[A](implicit a: Arbitrary[A]): A =
    Stream.continually(a.arbitrary(Gen.Parameters.default.withSize(5), Seed.random())).filter(_.isDefined).head.get

  def sampleN[A](size: Int)(implicit a: Arbitrary[A]): Seq[A] = List.fill(size)(sample)

  def sampleRange(min: Int, max: Int) =
    sample(Arbitrary(Gen.choose(min, max)))

  def sampleJson[A: Writes: Arbitrary]: JsValue =
    Json.toJson(sample[A])

  def sampleFilter[A](f: A => Boolean)(implicit arb: Arbitrary[A]): A = Stream.continually(sample[A]).filter(f).head

}
