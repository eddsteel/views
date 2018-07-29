package com.eddsteel.views.db
import _root_.cats.effect.IO
import _root_.cats.implicits._
import _root_.com.typesafe.scalalogging.StrictLogging
import _root_.doobie.implicits._

class PostgresWriter(pg: PostgresConnector) extends Store with StrictLogging {
  override def create[T: DBEntity](value: T): IO[(Long, T)] =
    for {
      _ <- IO(logger.info(s"Creating ${value.show}"))
      result <- Entity[T].insert(value).transact(pg.xa)
    } yield result

  override def list[T: DBEntity]: IO[Map[Long, T]] =
    for {
      result <- Entity[T].list.transact(pg.xa)
      _ <- IO(logger.info(s"Listing, got ${result.size} results"))
    } yield result
}
