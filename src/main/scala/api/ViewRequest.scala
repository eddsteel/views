package com.eddsteel.views
package api

import db.{Content, Medium, View}

final case class ViewRequest(
  name: String,
  creator: String,
  year: Int,
  medium: String,
  opinion: String
) {
  // TODO make safe
  def toDB: View = View(Content(name, creator, year, Medium.unsafeFromString(medium)), opinion)
}
