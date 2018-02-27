package rest.controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import com.google.gson.Gson
import reactivemongo.play.json._
import play.modules.reactivemongo.json.collection._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import play.modules.reactivemongo.{ // ReactiveMongo Play2 plugin
  MongoController,
  ReactiveMongoApi,
  ReactiveMongoComponents
}



case class Coupon(name: String,desc :String, _id: CouponId,img:String,code:String,poff:Int)
case class CouponId($oid: String)


@Singleton
  class CouponController @Inject() (val reactiveMongoApi: ReactiveMongoApi)
      extends Controller with MongoController with ReactiveMongoComponents {

def mongo = new backend.MongoRepo(reactiveMongoApi)

def index = Action.async { implicit request =>
  mongo.find().map(coupons => Ok(Json.toJson(coupons)))
}
  def show(id:String) = Action.async { implicit request =>
    mongo.findOne(id).map(coupons => Ok(Json.toJson(coupons)))
  }
  def delete(id:String) = Action.async { implicit request: Request[AnyContent] =>
    mongo.remove(id).map(le => Ok(Json.obj("success" -> le.ok)))
  }

  def edit(id:String) = Action.async { implicit request: Request[AnyContent] =>
    val gson = new Gson
    val content = request.body.asJson.get.toString
    val coupon = gson.fromJson(content, classOf[Coupon])
    val jbody = Json.obj("$set" -> Json.obj("name"->coupon.name,"desc"->coupon.desc,"img"->coupon.img,"code"->coupon.code,"poff"-> coupon.poff))
    mongo.update(id,jbody).map(le => Ok(Json.obj("success" -> le.ok)))
  }
  def create() = Action.async { implicit request: Request[AnyContent] =>
    val gson = new Gson
    val content = request.body.asJson.get.toString
    val coupon = gson.fromJson(content, classOf[Coupon])
    val jbody =  Json.obj("name"->coupon.name,"desc"->coupon.desc,"img"->coupon.img,"code"->coupon.code,"poff"-> coupon.poff)
    mongo.save(jbody).map(le => Ok(Json.obj("success" -> le.ok)))
  }
}
