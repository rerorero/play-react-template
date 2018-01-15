import javax.inject.{Inject, Provider, Singleton}

import com.typesafe.scalalogging.StrictLogging
import play.api.http.DefaultHttpErrorHandler
import play.api.mvc.{RequestHeader, Result, Results}
import play.api.routing.Router
import play.api.{Configuration, Environment, OptionalSourceMapper}

import scala.concurrent.Future

@Singleton
class ErrorHandlers @Inject() (
  env: Environment,
  config: Configuration,
  sourceMapper: OptionalSourceMapper,
  router: Provider[Router]
) extends DefaultHttpErrorHandler(env, config, sourceMapper, router) with StrictLogging {
//  override def onBadRequest(request: RequestHeader, message: String): Future[Result] =
//    Future.successful(Results.BadRequest(message))
//
//  override def onNotFound(request: RequestHeader, message: String): Future[Result] =
//    Future.successful(Results.NotFound(message))

  override def onServerError(request: RequestHeader, exception: Throwable): Future[Result] = {
    logger.error("Uncaught exception:",exception)
    Future.successful(Results.InternalServerError(exception.getMessage))
  }
}
