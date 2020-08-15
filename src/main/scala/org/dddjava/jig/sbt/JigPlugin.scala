package org.dddjava.jig.sbt

import org.dddjava.jig.domain.model.jigdocument.stationery.LinkPrefix
import sbt._

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
    jigApplicationPattern in jig := ".+\\.application\\..+",
    jigInfrastructurePattern in jig := ".+\\.infrastructure\\..+",
    jigPresentationPattern in jig := ".+\\.presentation\\..+",
    jigProjectPath in jig := "./",
    jigDirectoryClasses in jig := Jig.makeClasses().value,
    jigDirectoryResources in jig := Jig.makeClasses().value,
    jigDirectorySources in jig := "src/main/scala",
    jigLinkPrefix in jig := LinkPrefix.DISABLE
  )

  override lazy val buildSettings = Seq()

  override lazy val globalSettings = Seq()
}
