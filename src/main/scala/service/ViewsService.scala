package com.eddsteel.views
package service
// this is probably inappropriate, long term
import db.{Store, View}
import _root_.cats.effect.IO
import _root_.cats.data.EitherT

trait ViewsServiceOps {
  def createView(view: View): Result[(Long, View)]
  def listViews: Result[Map[Long, View]]
}

class ViewsService(store: Store) extends ViewsServiceOps {

  def createView(view: View): Result[(Long, View)] =
    handleStoreErrors(store.create(view))

  def listViews: Result[Map[Long, View]] =
    handleStoreErrors(store.list[View])

  def handleStoreErrors[A](storeOperation: => IO[A]): Result[A] =
    EitherT(storeOperation.attempt).leftMap {
      case t => UndiagnosedError(t)
    }
}
