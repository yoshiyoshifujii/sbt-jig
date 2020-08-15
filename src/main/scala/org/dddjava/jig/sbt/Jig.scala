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
          documentTypeText = (jigDocumentTypeText in key).value,
          outputDirectoryText = (jigOutputDirectoryText in key).value,
          outputOmitPrefix = (jigOutputOmitPrefix in key).value,
          modelPattern = (jigModelPattern in key).value,
          applicationPattern = (jigApplicationPattern in key).value,
          infrastructurePattern = (jigInfrastructurePattern in key).value,
          presentationPattern = (jigPresentationPattern in key).value,
          projectPath = (jigProjectPath in key).value,
          directoryClasses = (jigDirectoryClasses in key).value,
          directoryResources = (jigDirectoryResources in key).value,
          directorySources = (jigDirectorySources in key).value,
          linkPrefix = (jigLinkPrefix in key).value
        )
      )
    }

  def makeClasses(): Initialize[String] =
    Def.setting {
      s"target/scala-${scalaBinaryVersion.value}/classes"
    }

}
