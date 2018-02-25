package backend

import play.api.libs.json.{JsObject, Json}
import play.modules.reactivemongo.ReactiveMongoApi
import play.modules.reactivemongo.json.collection.JSONCollection
import reactivemongo.api.ReadPreference
import reactivemongo.api.commands.WriteResult
import reactivemongo.bson.{BSONObjectID, BSONDocument}

import scala.concurrent.{ExecutionContext, Future}

trait Repo {
  def find()(implicit ec: ExecutionContext): Future[List[JsObject]]

  def update(selector: BSONDocument, update: BSONDocument)(implicit ec: ExecutionContext): Future[WriteResult]

  def findOne(id:String)(implicit ec: ExecutionContext): Future[List[JsObject]]
  def remove(document: BSONDocument)(implicit ec: ExecutionContext): Future[WriteResult]

  def save(document: BSONDocument)(implicit ec: ExecutionContext): Future[WriteResult]
}

class MongoRepo(reactiveMongoApi: ReactiveMongoApi) extends Repo {
  // BSON-JSON conversions
  import play.modules.reactivemongo.json._

  protected def collection =
    reactiveMongoApi.db.collection[JSONCollection]("coupons")

  def find()(implicit ec: ExecutionContext): Future[List[JsObject]] =
      collection.find(Json.obj()).cursor[JsObject](ReadPreference.Primary).collect[List]()

  def findOne(id:String)(implicit ec: ExecutionContext): Future[List[JsObject]] =
     collection.find(Json.obj("_id" -> Json.obj("$oid" -> id))).cursor[JsObject](ReadPreference.Primary).collect[List]()

  def update(selector: BSONDocument, update: BSONDocument)(implicit ec: ExecutionContext): Future[WriteResult] = collection.update(selector, update)

  def remove(document: BSONDocument)(implicit ec: ExecutionContext): Future[WriteResult] = collection.remove(document)

  def save(document: BSONDocument)(implicit ec: ExecutionContext): Future[WriteResult] =
    collection.update(BSONDocument("_id" -> document.get("_id").getOrElse(BSONObjectID.generate)), document, upsert = true)
}
