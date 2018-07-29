package com.eddsteel.views
package api

import _root_.cats.implicits._
import db.View

final case class ViewResponse(
  id: Long,
  name: String,
  creator: String,
  year: Int,
  medium: String,
  opinion: String
)

object ViewResponse {
  def fromDB(id: Long, rest: View): ViewResponse =
    ViewResponse(
      id,
      rest.content.name,
      rest.content.creator,
      rest.content.year,
      rest.content.medium.show,
      rest.opinion)
}
