package org.dddjava.jig.scala

import java.nio.file.Path

import org.dddjava.jig.infrastructure.resourcebundle.Utf8ResourceBundle
import org.dddjava.jig.presentation.view.handler.HandlerMethodArgumentResolver
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._

object JigExecutor {

  def jigReports(): Unit = {
    val LOGGER = LoggerFactory.getLogger("org.dddjava.jig.scala.JigExecutor")

    val cliConfig = JigConfig()

    val jigMessages   = Utf8ResourceBundle.messageBundle()
    val jigDocuments  = cliConfig.jigDocuments()
    val configuration = cliConfig.configuration()

    LOGGER.info(
      "-- configuration -------------------------------------------\n{}\n------------------------------------------------------------",
      cliConfig.propertiesText()
    )

    val startTime = System.currentTimeMillis

    val implementationService = configuration.implementationService
    val jigDocumentHandlers   = configuration.documentHandlers

    val sourcePaths     = cliConfig.rawSourceLocations()
    val implementations = implementationService.implementations(sourcePaths)

    val status = implementations.status
    if (status.hasError) {
      LOGGER.warn(jigMessages.getString("failure"))
      status.listErrors().asScala.foreach { analyzeStatus =>
        LOGGER.warn(jigMessages.getString("failure.details"), jigMessages.getString(analyzeStatus.messageKey))
      }
      throw new RuntimeException("failure")
    }

    if (status.hasWarning) {
      LOGGER.warn(jigMessages.getString("implementation.warning"))
      status.listWarning().asScala.foreach { analyzeStatus =>
        LOGGER
          .warn(jigMessages.getString("implementation.warning.details"),
                jigMessages.getString(analyzeStatus.messageKey))
      }
    }

    val outputDirectory: Path = cliConfig.outputDirectory()

    val handleResultList =
      jigDocuments.map { jigDocument =>
        jigDocumentHandlers.handle(jigDocument, new HandlerMethodArgumentResolver(implementations), outputDirectory)
      }

    val resultLog = handleResultList
      .filter(_.success)
      .map { handleResult =>
        handleResult.jigDocument + " : " + handleResult.outputFilePaths
      }.mkString("\n")

    LOGGER.info(
      "-- output documents -------------------------------------------\n{}\n------------------------------------------------------------",
      resultLog
    )
    LOGGER.info(jigMessages.getString("success"), System.currentTimeMillis - startTime)
  }

}
