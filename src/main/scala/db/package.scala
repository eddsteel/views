package com.eddsteel.views
import _root_.cats.Show

package object db {
  type DBEntity[T] = Entity[T] with Show[T]
  type Year = Int

  object DBEntity {
    def apply[T](implicit evShow: Show[T], evEntity: Entity[T]): DBEntity[T] =
      new Object with Show[T] with Entity[T] {
        override def show(t: T) = evShow.show(t)
        override def insert = evEntity.insert
        override def list = evEntity.list
      }
  }
}
