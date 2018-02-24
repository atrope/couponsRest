package rest.controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import com.google.gson.Gson
import java.util.ArrayList

case class Coupon(name: String, id: Long)

@Singleton
class CouponController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def index() = Action { implicit request: Request[AnyContent] =>
    val gson = new Gson
    val coupons : ArrayList[Coupon] = new ArrayList[Coupon]
    var a = 0;
    for( a <- 1 to 18) coupons.add(Coupon("teste #" +a ,a))
    Ok(gson.toJson(coupons))
  }
  def show(id:Long) = Action { implicit request: Request[AnyContent] =>
    val gson = new Gson
    Ok(gson.toJson(Coupon("teste #" +id ,id)))
  }
}
