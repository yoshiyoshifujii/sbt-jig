package org.dddjava.jig.sbt

import sbt.{ Def, _ }
import Keys._
import Def.Initialize
import org.dddjava.jig.scala.{ JigConfig, JigExecutor }

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
          projectPath = (jigProjectPath in key).value,
          directoryClasses = (jigDirectoryClasses in key).value,
          directoryResources = (jigDirectoryResources in key).value,
          directorySources = (jigDirectorySources in key).value
        )
      )
    }

  def makeClasses(): Initialize[String] =
    Def.setting {
      val sbv = scalaBinaryVersion.value
      s"target/scala-$sbv/classes"
    }

}
