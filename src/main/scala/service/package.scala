package com.eddsteel.views
import _root_.cats.effect.IO
import _root_.cats.data.EitherT

package object service {
  type Result[A] = EitherT[IO, ServiceError, A]
}
