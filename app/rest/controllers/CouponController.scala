package rest.controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import com.google.gson.Gson
import java.util.ArrayList

case class Coupon(name: String,desc :String, id: Long,img:String,code:String)

@Singleton
class CouponController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def index() = Action { implicit request: Request[AnyContent] =>
    val gson = new Gson
    val coupons : ArrayList[Coupon] = new ArrayList[Coupon]
    var a = 0;
    val r = scala.util.Random
    val md = java.security.MessageDigest.getInstance("SHA-1")
    val code = md.digest(r.nextInt.toString.getBytes("UTF-8")).map("%02x".format(_)).mkString
    for( a <- 1 to 18) coupons.add(Coupon("teste #" +a ,"Desc for #"+a,a,"https://picsum.photos/200/300",code))
    Ok(gson.toJson(coupons))
  }
  def show(id:Long) = Action { implicit request: Request[AnyContent] =>
    val gson = new Gson
    val r = scala.util.Random
    val md = java.security.MessageDigest.getInstance("SHA-1")
    val code = md.digest(r.nextInt.toString.getBytes("UTF-8")).map("%02x".format(_)).mkString

    Ok(gson.toJson(Coupon("teste #" +id ,"Desc for #"+id,id,"https://picsum.photos/300/300",code)))
  }
  def delete(id:Long) = Action { implicit request: Request[AnyContent] =>
    val rnd = new scala.util.Random
    if (rnd.nextInt(2)==0) Ok("")
    else NotFound("")
  }

  def edit(id:Long) = Action { implicit request: Request[AnyContent] =>
    val gson = new Gson
    val content = request.body.asJson.get.toString
    val coupon = gson.fromJson(content, classOf[Coupon])
    val rnd = new scala.util.Random
    if (rnd.nextInt(2)==0) Ok("")
    else NotFound("")
  }
  def create() = Action { implicit request: Request[AnyContent] =>
    val gson = new Gson
    val content = request.body.asJson.get.toString
    val coupon = gson.fromJson(content, classOf[Coupon])
    val rnd = new scala.util.Random
    if (rnd.nextInt(2) == 0) Ok("")
    else NotFound("")
  }
}
