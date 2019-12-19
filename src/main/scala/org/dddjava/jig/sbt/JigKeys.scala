package org.dddjava.jig.sbt

import sbt._

trait JigKeys {

  lazy val jig = taskKey[Unit]("Java Instant-document Gazer")
  lazy val jigReports = taskKey[Unit]("Generates JIG documentation for the main source code.")

}
