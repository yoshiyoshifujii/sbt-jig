package org.dddjava.jig.sbt

import sbt._
import sbt.Keys._
import sbt.plugins.JvmPlugin

object JigPlugin extends AutoPlugin {

  override def trigger = allRequirements

  object autoImport extends JigKeys

  import autoImport._

  override lazy val projectSettings = Seq(
    jigReports := Jig.jigReportsTask(jig).value
  )

  override lazy val buildSettings = Seq()

  override lazy val globalSettings = Seq()
}
