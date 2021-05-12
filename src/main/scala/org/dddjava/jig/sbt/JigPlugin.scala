package org.dddjava.jig.sbt

import org.dddjava.jig.domain.model.documents.documentformat.{ JigDiagramFormat, JigDocument }
import org.dddjava.jig.domain.model.documents.stationery.LinkPrefix
import sbt._

import scala.collection.JavaConverters._

object JigPlugin extends AutoPlugin {

  override def trigger = allRequirements

  object autoImport extends JigKeys

  import autoImport._

  override lazy val projectSettings = Seq(
    jigReports := Jig.jigReportsTask(jig).value,
    jig / jigDocuments := JigDocument.canonical().asScala,
    jig / jigOutputDirectoryText := "./target/jig",
    jig / jigOmitPrefix := ".+\\.(service|domain\\.(model|type))\\.",
    jig / jigPatternDomain := ".+\\.domain\\.(model|type)\\..+",
    jig / jigOutputDiagramFormat := JigDiagramFormat.SVG,
    jig / jigLinkPrefix := LinkPrefix.DISABLE,
    jig / jigProjectPath := "./",
    jig / jigDirectoryClasses := Jig.makeClasses().value,
    jig / jigDirectoryResources := Jig.makeClasses().value,
    jig / jigDirectorySources := "src/main/scala"
  )

  override lazy val buildSettings = Seq()

  override lazy val globalSettings = Seq()
}
