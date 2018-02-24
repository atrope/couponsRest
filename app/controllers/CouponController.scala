package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._

@Singleton
class CouponController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok("hello world")
  }
  def show(id:Long) = Action { implicit request: Request[AnyContent] =>
    Ok(Json.obj("coupon" -> id ))
    //NotFound(":()")
  }
}
