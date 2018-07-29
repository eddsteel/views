package com.eddsteel.views
package api

import _root_.cats.effect._
import _root_.cats.implicits._
import _root_.com.typesafe.scalalogging.LazyLogging
import _root_.org.http4s._
import _root_.org.http4s.dsl.io._
import _root_.org.http4s.server.blaze._
import _root_.org.http4s.circe._
import _root_.io.circe.generic.auto._
import _root_.io.circe.syntax._
import _root_.io.circe.Encoder
import service.{Result, UndiagnosedError, ViewsServiceOps}

@SuppressWarnings(Array("org.wartremover.warts.PublicInference"))
class ViewsAPI(views: ViewsServiceOps) extends LazyLogging {

  implicit val jsonDecoder: EntityDecoder[IO, ViewRequest] = jsonOf[IO, ViewRequest]

  val service: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "views" =>
      val result: Result[Iterable[ViewResponse]] = for {
        result <- views.listViews
        resp = result.map(Function.tupled(ViewResponse.fromDB _))
      } yield resp

      response(result)

    case req @ POST -> Root / "views" =>
      val result: Result[ViewResponse] = for {
        request <- req.as[ViewRequest].attemptT.leftMap(UndiagnosedError.apply)
        idView <- views.createView(request.toDB)
        resp = ViewResponse.fromDB(idView._1, idView._2)
      } yield resp

      response(result)

  }

  def response[A: Encoder](result: Result[A]): IO[Response[IO]] = result.value.flatMap {
    case Left(UndiagnosedError(error)) =>
      logger.error("Something went wrong", error)
      InternalServerError("Something went wrong.")
    case Right(resp) => Ok(resp.asJson)
  }

  def mount(builder: BlazeBuilder[IO]): BlazeBuilder[IO] =
    builder.mountService(service, "/")
}
