package org.dddjava.jig.scala

import org.dddjava.jig.infrastructure.resourcebundle.Utf8ResourceBundle

import java.nio.file.Path
import scala.collection.JavaConverters._

object JigExecutor {

  def jigReports(cliConfig: JigConfig): Unit = {

    val jigMessages   = Utf8ResourceBundle.messageBundle()
    val configuration = cliConfig.configuration()

    println(
      s"-- configuration -------------------------------------------\n${cliConfig.propertiesText()}\n------------------------------------------------------------"
    )

    val startTime = System.currentTimeMillis

    val jigSourceReadService = configuration.implementationService
    val jigDocumentHandlers  = configuration.documentHandlers

    val sourcePaths  = cliConfig.rawSourceLocations()
    val readStatuses = jigSourceReadService.readSourceFromPaths(sourcePaths)

    if (readStatuses.hasError) {
      println(jigMessages.getString("failure"))
      readStatuses.listErrors().asScala.foreach { readStatus =>
        println(jigMessages.getString("failure.details"), jigMessages.getString(readStatus.messageKey))
      }
      throw new RuntimeException("failure")
    }

    if (readStatuses.hasWarning) {
      println(jigMessages.getString("implementation.warning"))
      readStatuses.listWarning().asScala.foreach { readStatus =>
        println(
          jigMessages.getString("implementation.warning.details"),
          jigMessages.getString(readStatus.messageKey)
        )
      }
    }

    val outputDirectory: Path = cliConfig.outputDirectory()
    val handleResultList      = jigDocumentHandlers.handleJigDocuments(configuration.jigDocuments(), outputDirectory)

    val resultLog = handleResultList.asScala
      .filter(_.success)
      .map { handleResult => handleResult.jigDocument + " : " + handleResult.outputFilePathsText() }.mkString("\n")

    println(
      s"-- output documents -------------------------------------------\n${resultLog}\n------------------------------------------------------------"
    )
    println(jigMessages.getString("success"), System.currentTimeMillis - startTime)
  }

}
