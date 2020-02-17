package org.dddjava.jig.scala

import java.nio.file.Path

import org.dddjava.jig.infrastructure.resourcebundle.Utf8ResourceBundle
import org.dddjava.jig.presentation.view.handler.HandlerMethodArgumentResolver

import scala.collection.JavaConverters._

object JigExecutor {

  def jigReports(cliConfig: JigConfig): Unit = {

    val jigMessages   = Utf8ResourceBundle.messageBundle()
    val jigDocuments  = cliConfig.jigDocuments()
    val configuration = cliConfig.configuration()

    println(
      s"-- configuration -------------------------------------------\n${cliConfig.propertiesText()}\n------------------------------------------------------------"
    )

    val startTime = System.currentTimeMillis

    val implementationService = configuration.implementationService
    val jigDocumentHandlers   = configuration.documentHandlers

    val sourcePaths     = cliConfig.rawSourceLocations()
    val implementations = implementationService.implementations(sourcePaths)

    val status = implementations.status
    if (status.hasError) {
      println(jigMessages.getString("failure"))
      status.listErrors().asScala.foreach { analyzeStatus =>
        println(jigMessages.getString("failure.details"), jigMessages.getString(analyzeStatus.messageKey))
      }
      throw new RuntimeException("failure")
    }

    if (status.hasWarning) {
      println(jigMessages.getString("implementation.warning"))
      status.listWarning().asScala.foreach { analyzeStatus =>
        println(
          jigMessages.getString("implementation.warning.details"),
          jigMessages.getString(analyzeStatus.messageKey)
        )
      }
    }

    val outputDirectory: Path = cliConfig.outputDirectory()

    val handleResultList =
      jigDocuments.map { jigDocument =>
        jigDocumentHandlers.handle(jigDocument, new HandlerMethodArgumentResolver(implementations), outputDirectory)
      }

    val resultLog = handleResultList
      .filter(_.success)
      .map { handleResult => handleResult.jigDocument + " : " + handleResult.outputFilePaths }.mkString("\n")

    println(
      s"-- output documents -------------------------------------------\n${resultLog}\n------------------------------------------------------------"
    )
    println(jigMessages.getString("success"), System.currentTimeMillis - startTime)
  }

}
