package com.eddsteel.views.service

sealed trait ServiceError
final case class UndiagnosedError(t: Throwable) extends ServiceError
