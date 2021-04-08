package org.dddjava.jig.sbt

import org.dddjava.jig.scala.{ JigConfig, JigExecutor }
import sbt.Def.Initialize
import sbt.Keys._
import sbt.{ Def, _ }

object Jig {

  import JigPlugin.autoImport._

  def jigReportsTask(key: TaskKey[Unit]): Initialize[Task[Unit]] =
    Def.task {
      JigExecutor.jigReports(
        JigConfig(
          documentTypeText = (key / jigDocumentTypeText).value,
          outputDirectoryText = (key / jigOutputDirectoryText).value,
          outputOmitPrefix = (key / jigOutputOmitPrefix).value,
          modelPattern = (key / jigModelPattern).value,
          applicationPattern = (key / jigApplicationPattern).value,
          infrastructurePattern = (key / jigInfrastructurePattern).value,
          presentationPattern = (key / jigPresentationPattern).value,
          projectPath = (key / jigProjectPath).value,
          directoryClasses = (key / jigDirectoryClasses).value,
          directoryResources = (key / jigDirectoryResources).value,
          directorySources = (key / jigDirectorySources).value,
          linkPrefix = (key / jigLinkPrefix).value
        )
      )
    }

  def makeClasses(): Initialize[String] =
    Def.setting {
      s"target/scala-${scalaBinaryVersion.value}/classes"
    }

}
