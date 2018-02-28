package backend

import play.api.libs.json.{JsObject, Json}
import play.modules.reactivemongo.ReactiveMongoApi
import play.modules.reactivemongo.json.collection.JSONCollection
import reactivemongo.api.ReadPreference
import reactivemongo.api.commands.WriteResult
import play.modules.reactivemongo.json._
import scala.concurrent.{ExecutionContext, Future}

trait Mongo {
  def find()(implicit ec: ExecutionContext): Future[List[JsObject]]
  def update(id: String, update: JsObject)(implicit ec: ExecutionContext): Future[WriteResult]
  def findOne(id: String)(implicit ec: ExecutionContext): Future[List[JsObject]]
  def remove(id: String)(implicit ec: ExecutionContext): Future[WriteResult]
  def save(document: JsObject)(implicit ec: ExecutionContext): Future[WriteResult]
}

class MongoRepo(reactiveMongoApi: ReactiveMongoApi) extends Mongo {
  protected def collection = reactiveMongoApi.db.collection[JSONCollection]("coupons")
  def find()(implicit ec: ExecutionContext): Future[List[JsObject]] =c ollection.find(Json.obj()).cursor[JsObject](ReadPreference.Primary).collect[List]()

  def findOne(id: String)(implicit ec: ExecutionContext): Future[List[JsObject]] = collection.find(Json.obj("_id" -> Json.obj("$oid" -> id))).cursor[JsObject](ReadPreference.Primary).collect[List]()

  def update(id: String, update: JsObject)(implicit ec: ExecutionContext): Future[WriteResult] = collection.update(Json.obj("_id" -> Json.obj("$oid" -> id)), update)

  def remove(id:String)(implicit ec: ExecutionContext): Future[WriteResult] = collection.remove(Json.obj("_id" -> Json.obj("$oid" -> id)))

  def save(document: JsObject)(implicit ec: ExecutionContext): Future[WriteResult] = collection.insert(document)
}
