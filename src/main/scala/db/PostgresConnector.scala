package com.eddsteel.views
package db
import _root_.doobie._
import _root_.cats.effect.IO
import config.PostgresConfig

class PostgresConnector(config: PostgresConfig) {
  val xa: Transactor[IO] =
    Transactor.fromDriverManager[IO](config.driver, config.url, config.username, config.password)
}
