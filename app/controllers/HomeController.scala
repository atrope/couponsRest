package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import com.google.gson.Gson
import scalaj.http._

case class Coupon(name: String,desc :String, _id: CouponId,img:String,code:String,poff:Int)
case class CouponId($oid: String)
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def index() = Action { implicit request: Request[AnyContent] =>
    val url = "http://localhost:9000/coupons"
    val result = Http(url).asString
    val gson = new Gson
    val coupons = gson.fromJson(result.body, classOf[Array[Coupon]])
    Ok(views.html.index(coupons))
  }
  def show(id:String) = Action { implicit request: Request[AnyContent] =>
    val url = "http://localhost:9000/coupons/"+id
    val result = Http(url).asString
    val gson = new Gson
    val coupon = gson.fromJson(result.body, classOf[Array[Coupon]])
    Ok(views.html.promo(coupon(0)))
  }
}
