package com.eddsteel.views.db
import _root_.cats.effect.IO

trait Store {
  def create[T: DBEntity](value: T): IO[(Long, T)]
  def list[T: DBEntity]: IO[Map[Long, T]]
}
