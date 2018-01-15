package domain.mode.base

import domain.model.base.{EnumValue, EnumValueCompanion}
import org.scalatest.FunSpec
import play.api.libs.json.{JsError, JsSuccess, Json}

class EnumValueSpec extends FunSpec {
  sealed abstract class TestEnum(value: String) extends EnumValue[String](value)
  object TestA extends TestEnum("a")
  object TestB extends TestEnum("b")
  object TestC extends TestEnum("c")

  object TestEnum extends EnumValueCompanion[String, TestEnum] {
    override def values: Set[TestEnum] = Set(TestA, TestB)
  }

  describe("IpV4") {
    it ("列挙値であればパースできる") {
      assert(JsSuccess(TestA) === Json.fromJson[TestEnum](Json.parse("\"a\"")))
      assert(JsSuccess(TestB) === Json.fromJson[TestEnum](Json.parse("\"b\"")))
    }
    it ("列挙値でなければパースできない") {
      assert(!Json.fromJson[TestEnum](Json.parse("\"c\"")).isSuccess)
      assert(!Json.fromJson[TestEnum](Json.parse("\"d\"")).isSuccess)
    }
  }
}
