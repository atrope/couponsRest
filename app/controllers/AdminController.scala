package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import com.google.gson.Gson
import scalaj.http._

@Singleton
class AdminController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def index() = Action { implicit request: Request[AnyContent] =>
    val url = "http://localhost:9000/coupons"
    val result = Http(url).asString
    val gson = new Gson
    val coupons = gson.fromJson(result.body, classOf[Array[Coupon]])
    Ok(views.html.admin.index(coupons))
  }
  def showedit(id:String) = Action { implicit request: Request[AnyContent] =>
    val url = "http://localhost:9000/coupons/"+id
    val result = Http(url).asString
    val gson = new Gson
    val coupon = gson.fromJson(result.body, classOf[Array[Coupon]])
    Ok(views.html.admin.edit(coupon(0)))
  }
  def showcreate() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.admin.add())
  }

  def delete() = Action { implicit request: Request[AnyContent] =>
    val id = request.body.asFormUrlEncoded.get("id")(0)
    val url = "http://localhost:9000/coupons/"+id
    val result = Http(url).method("DELETE").asString
    if (result.code == 200) Ok("")
    else NotFound("")
  }

  def edit() = Action { implicit request: Request[AnyContent] =>
    val title = request.body.asFormUrlEncoded.get("title")(0)
    val desc = request.body.asFormUrlEncoded.get("desc")(0)
    val img = request.body.asFormUrlEncoded.get("img")(0)
    val code = request.body.asFormUrlEncoded.get("code")(0)
    val id = request.body.asFormUrlEncoded.get("id")(0)
    val poff = request.body.asFormUrlEncoded.get("poff")(0).toInt
    val url = "http://localhost:9000/coupons/"+id
    val gson = new Gson
    val result = Http(url).put(gson.toJson(Coupon(title,desc,CouponId(id),img,code,poff))).header("content-type", "application/json").method("PUT").asString
    if (result.code == 200) Ok("")
    else NotFound("")
  }
  def create() = Action { implicit request: Request[AnyContent] =>
    val title = request.body.asFormUrlEncoded.get("title")(0)
    val desc = request.body.asFormUrlEncoded.get("desc")(0)
    val img = request.body.asFormUrlEncoded.get("img")(0)
    val code = request.body.asFormUrlEncoded.get("code")(0)
    val poff = request.body.asFormUrlEncoded.get("poff")(0).toInt

    val id = "0"
    val url = "http://localhost:9000/coupons"
    val gson = new Gson
    val result = Http(url).put(gson.toJson(Coupon(title,desc,CouponId(id),img,code,poff))).header("content-type", "application/json").method("POST").asString
    if (result.code == 200) Ok("")
    else NotFound("")
  }
}
