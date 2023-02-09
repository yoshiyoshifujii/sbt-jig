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
          documents = (key / jigDocuments).value,
          modelPattern = (key / jigPatternDomain).value,
          outputDirectoryText = (key / jigOutputDirectoryText).value,
          diagramFormat = (key / jigOutputDiagramFormat).value,
          omitPrefix = (key / jigOmitPrefix).value,
          linkPrefix = (key / jigLinkPrefix).value,
          projectPath = (key / jigProjectPath).value,
          directoryClasses = (key / jigDirectoryClasses).value,
          directoryResources = (key / jigDirectoryResources).value,
          directorySources = (key / jigDirectorySources).value
        )
      )
    }

  def makeClasses(): Initialize[String] =
    Def.setting {
      val sv          = scalaVersion.value
      val scalaSuffix = if (ScalaArtifacts.isScala3(sv)) sv else scalaBinaryVersion.value
      s"target/scala-$scalaSuffix/classes"
    }

}
