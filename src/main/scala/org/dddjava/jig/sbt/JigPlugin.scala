package org.dddjava.jig.sbt

import org.dddjava.jig.domain.model.jigdocument.stationery.LinkPrefix
import sbt._

object JigPlugin extends AutoPlugin {

  override def trigger = allRequirements

  object autoImport extends JigKeys

  import autoImport._

  override lazy val projectSettings = Seq(
    jigReports := Jig.jigReportsTask(jig).value,
    jig / jigDocumentTypeText := "",
    jig / jigOutputDirectoryText := "./target/jig",
    jig / jigOutputOmitPrefix := ".+\\.(service|domain\\.(model|type))\\.",
    jig / jigModelPattern := ".+\\.domain\\.(model|type)\\..+",
    jig / jigApplicationPattern := ".+\\.application\\..+",
    jig / jigInfrastructurePattern := ".+\\.infrastructure\\..+",
    jig / jigPresentationPattern := ".+\\.presentation\\..+",
    jig / jigProjectPath := "./",
    jig / jigDirectoryClasses := Jig.makeClasses().value,
    jig / jigDirectoryResources := Jig.makeClasses().value,
    jig / jigDirectorySources := "src/main/scala",
    jig / jigLinkPrefix := LinkPrefix.DISABLE
  )

  override lazy val buildSettings = Seq()

  override lazy val globalSettings = Seq()
}
