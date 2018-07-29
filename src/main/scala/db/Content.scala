package com.eddsteel.views.db
import _root_.cats.Show
import _root_.doobie.Meta
import _root_.doobie.implicits._

final case class Content(name: String, creator: String, year: Year, medium: Medium)

object Content {
  implicit val show: Show[Content] = Show.fromToString
  implicit val entity: Entity[Content] = Entity.create(
    c =>
      sql"""INSERT INTO content (id, name, creator, year, medium)
          VALUES (default, ${c.name}, ${c.creator}, ${c.year}, ${c.medium}::medium)
          """.update
        .withUniqueGeneratedKeys[(Long, Content)]("id", "name", "creator", "year", "medium"),
    sql"""SELECT id, name, creator, year, medium FROM content"""
      .query[(Long, Content)]
      .to[List]
      .map(_.toMap)
  )
}

sealed trait Medium extends Product with Serializable
object Medium {
  final case object Film extends Medium { override val toString: String = "film" }
  final case object Book extends Medium { override val toString: String = "book" }

  def unsafeFromString(s: String): Medium = s match {
    case "film" => Film
    case "book" => Book
  }

  implicit val show: Show[Medium] = Show.fromToString
  implicit val meta: Meta[Medium] = Meta[String].xmap(unsafeFromString, m => show.show(m))
}
