package domain.model

object Exceptions {
  case class InvalidParam(msg: String) extends Exception(msg)
}
