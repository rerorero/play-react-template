package domain.model.base

import domain.model.Exceptions.InvalidParam
import play.api.libs.json._

import scala.util.{Failure, Success, Try}

trait Value[A] {
  val value: A
}

abstract class ValueCompanion[A, B <: Value[A]](implicit f: Format[A]) {
  def convert: PartialFunction[A, B]

  // Override to customize the error message.
  def convertErrorMessage(value: A): String = s"Invalid format error(${value})"

  implicit val format: Format[B] = Format(
    Reads { json =>
      f.reads(json).flatMap { a =>
        convert.lift.apply(a) match {
          case Some(v) => JsSuccess(v)
          case None => JsError(JsonValidationError(convertErrorMessage(a)))
        }
      }
    },
    Writes(b => f.writes(b.value))
  )

  def forceConvert(a: A): Try[B] = {
    convert.lift.apply(a) match {
      case Some(v) => Success(v)
      case None => Failure(InvalidParam(convertErrorMessage(a)))
    }
  }
}
