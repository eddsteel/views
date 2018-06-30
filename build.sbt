// scalafmt: {align.tokens = ["%", "%%", ":=", "+="]}
name         := "views"
description  := "Reviews of things (to try out a modern scala stack)"
scalaVersion := "2.12.4"
organization := "com.eddsteel"
version      := "slice1"

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

libraryDependencies += "org.typelevel" %% "cats-core" % "1.1.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"

Lint.settings
Flags.settings
Format.settings
