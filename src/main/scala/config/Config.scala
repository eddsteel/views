package com.eddsteel.views.config

import _root_.com.softwaremill.macwire.Module

@Module
class Config {
  val postgres: PostgresConfig = pureconfig.loadConfigOrThrow[PostgresConfig]("postgres")
  val http: HttpServerConfig = pureconfig.loadConfigOrThrow[HttpServerConfig]("http")
}
