package org.dddjava.jig.sbt

import sbt._
import sbt.Keys._
import sbt.plugins.JvmPlugin

object JigPlugin extends AutoPlugin {

  override def trigger = allRequirements

  object autoImport extends JigKeys

  import autoImport._

  override lazy val projectSettings = Seq(
    jigReports := Jig.jigReportsTask(jig).value,
    jigDocumentTypeText in jig := "",
    jigOutputDirectoryText in jig := "./target/jig",
    jigOutputOmitPrefix in jig := ".+\\.(service|domain\\.(model|type))\\.",
    jigModelPattern in jig := ".+\\.domain\\.(model|type)\\..+",
    jigProjectPath in jig := "./",
    jigDirectoryClasses in jig := Jig.makeClasses().value,
    jigDirectoryResources in jig := Jig.makeClasses().value,
    jigDirectorySources in jig := "src/main/scala"
  )

  override lazy val buildSettings = Seq()

  override lazy val globalSettings = Seq()
}
