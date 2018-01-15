package controllers

import javax.inject._

import com.typesafe.scalalogging.StrictLogging
import domain.model.value.ManageConfig
import play.api.mvc._

@Singleton
class FrontController @Inject()(cc: ControllerComponents, env: play.Environment)
  extends AbstractController(cc) with StrictLogging {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.front(ManageConfig.env))
  }

  def indexWithPath(path: String) = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.front(ManageConfig.env))
  }
}

