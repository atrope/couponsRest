package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import com.google.gson.Gson

case class Coupon(name: String, id: Long)
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def index() = Action { implicit request: Request[AnyContent] =>
    val url = "http://localhost:9000/coupons"
    val result = scala.io.Source.fromURL(url).mkString
    val gson = new Gson
    val coupons = gson.fromJson(result, classOf[Array[Coupon]])
    Ok(views.html.index(coupons))
  }
  def show(id:Long) = Action { implicit request: Request[AnyContent] =>
    val url = "http://localhost:9000/coupons/"+id
    val result = scala.io.Source.fromURL(url).mkString
    val gson = new Gson
    val coupon = gson.fromJson(result, classOf[Coupon])
    Ok(views.html.promo(coupon))
  }
}
