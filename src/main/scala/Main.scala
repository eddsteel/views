package com.eddsteel.views
import _root_.cats.effect.IO
import _root_.com.softwaremill.macwire.wire
import _root_.fs2.{Stream, StreamApp}
import _root_.org.http4s.server.blaze._
import _root_.scala.concurrent.ExecutionContext.Implicits.global
import _root_.com.typesafe.scalalogging.LazyLogging
import api._
import config.Config
import db._
import service._

object Main extends StreamApp[IO] with LazyLogging {
  private val config: Config = wire[Config]
  private val postgres: PostgresConnector = wire[PostgresConnector]
  private val db: Store = wire[PostgresWriter]
  private val service: ViewsServiceOps = wire[ViewsService]
  private val api: ViewsAPI = wire[ViewsAPI]

  def stream(args: List[String], requestShutdown: IO[Unit]): Stream[IO, StreamApp.ExitCode] = {
    val webServer = BlazeBuilder[IO].bindHttp(config.http.port, config.http.host)
    val webAPI = api.mount(webServer)

    logger.info("Bound to :8080")
    webAPI.serve
  }
}
