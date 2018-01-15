package controllers

import javax.inject._

import com.typesafe.scalalogging.StrictLogging
import play.api.libs.json.Json
import play.api.mvc._

@Singleton
class HelloController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with StrictLogging {

  def say() = Action(parse.json) {
    Ok(Json.obj("data" -> "Hello, world."))
  }
}
