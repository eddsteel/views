package com.eddsteel.views.db

import _root_.cats.Show
import _root_.doobie.implicits._
import _root_.doobie.util.log.LogHandler

final case class View(content: Content, opinion: String)

object View {

  implicit val handler: LogHandler = LogHandler(e => Console.println(s"*** $e"))

  implicit val show: Show[View] = Show.fromToString
  implicit val entity: Entity[View] = Entity.create(
    e =>
      for {
        cResult <- Content.entity.insert(e.content)
        (contentID, insertedContent) = cResult
        vResult <- sql"""INSERT INTO view (content_id, opinion)
                      VALUES (${contentID}, ${e.opinion})""".update
          .withUniqueGeneratedKeys[(Long, String)]("id", "opinion")
        (id, opinion) = vResult
      } yield id -> View(insertedContent, opinion),
    for {
      contentMap <- Content.entity.list
      allViews <- sql"SELECT id, content_id, opinion from view".query[(Long, Long, String)].to[List]
    } yield
      allViews.map {
        case (id, cid, opinion) =>
          id -> View(contentMap(cid), opinion)
      }.toMap
  )

  implicit val dbEntity: DBEntity[View] = DBEntity[View](show, entity)
}
