// scalafmt: {align.tokens = ["%", "%%", ":=", "+="]}
name                            := "views"
description                     := "Reviews of things (to try out a modern scala stack)"
scalaVersion                    := "2.12.6"
ensimeScalaVersion in ThisBuild := "2.12.6"
organization                    := "com.eddsteel"
version                         := "slice1"

licenses := List(
  ("GPL version 3", url("https://www.gnu.org/licenses/gpl-3.0.en.html")))
homepage := Some(url("https://github.com/eddsteel/views"))
developers := List(
  Developer("eddsteel",
            "Edd Steel",
            "edward.steel@gmail.com",
            url("https://github.com/eddsteel/views")))
scmInfo := Some(
  ScmInfo(url("https://github.com/eddsteel/views"),
          "scm:git:https://github.com/eddsteel/views.git"))

libraryDependencies += "org.typelevel"              %% "cats-core"           % "1.1.0"
libraryDependencies += "org.tpolecat"               %% "doobie-core"         % "0.5.3"
libraryDependencies += "org.tpolecat"               %% "doobie-postgres"     % "0.5.3"
libraryDependencies += "org.tpolecat"               %% "doobie-scalatest"    % "0.5.3"
libraryDependencies += "com.softwaremill.macwire"   %% "macros"              % "2.3.1"
libraryDependencies += "com.softwaremill.macwire"   %% "util"                % "2.3.1"
libraryDependencies += "org.scalatest"              %% "scalatest"           % "3.0.4" % "test"
libraryDependencies += "com.github.pureconfig"      %% "pureconfig-akka"     % "0.9.1"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging"       % "3.9.0"
libraryDependencies += "org.http4s"                 %% "http4s-dsl"          % "0.19.0-M1"
libraryDependencies += "org.http4s"                 %% "http4s-blaze-server" % "0.19.0-M1"
libraryDependencies += "org.http4s"                 %% "http4s-blaze-client" % "0.19.0-M1"
libraryDependencies += "org.http4s"                 %% "http4s-circe"        % "0.19.0-M1"
libraryDependencies += "io.circe"                   %% "circe-core"          % "0.10.0-M1"
libraryDependencies += "io.circe"                   %% "circe-generic"       % "0.10.0-M1"
libraryDependencies += "ch.qos.logback"             % "logback-classic"      % "1.2.3"

// TODO: why does C-c on run still stop SBT?
fork in (Compile, run) := true

Lint.settings
Flags.settings
Format.settings
