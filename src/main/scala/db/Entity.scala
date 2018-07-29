package com.eddsteel.views.db
import _root_.doobie._

trait Entity[T] {

  /** How to insert/return one of these */
  def insert: T => ConnectionIO[(Long, T)]
  def list: ConnectionIO[Map[Long, T]]
}

object Entity {
  def apply[T: Entity]: Entity[T] = implicitly[Entity[T]]

  def create[T](
    insertF: T => ConnectionIO[(Long, T)],
    listF: ConnectionIO[Map[Long, T]]): Entity[T] =
    new Entity[T] {
      def insert = insertF
      def list = listF
    }
}
