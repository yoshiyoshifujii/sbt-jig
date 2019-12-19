package org.dddjava.jig.sbt

import sbt._

trait JigKeys {

  lazy val jig        = taskKey[Unit]("Java Instant-document Gazer")
  lazy val jigReports = taskKey[Unit]("Generates JIG documentation for the main source code.")

  lazy val jigDocumentTypeText    = settingKey[String]("")
  lazy val jigOutputDirectoryText = settingKey[String]("")
  lazy val jigOutputOmitPrefix    = settingKey[String]("")
  lazy val jigModelPattern        = settingKey[String]("")
  lazy val jigProjectPath         = settingKey[String]("")
  lazy val jigDirectoryClasses    = settingKey[String]("")
  lazy val jigDirectoryResources  = settingKey[String]("")
  lazy val jigDirectorySources    = settingKey[String]("")

}
