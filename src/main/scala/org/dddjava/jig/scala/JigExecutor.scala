package org.dddjava.jig.scala

import java.nio.file.Path
import scala.collection.JavaConverters._

object JigExecutor {

  def jigReports(cliConfig: JigConfig): Unit = {

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
      readStatuses.listErrors().asScala.foreach { readStatus =>
        println(s"${readStatus.localizedMessage()}")
      }
      throw new RuntimeException("failure")
    }

    if (readStatuses.hasWarning) {
      readStatuses.listWarning().asScala.foreach { readStatus =>
        println(s"${readStatus.localizedMessage()}")
      }
    }

    val handleResultList = jigDocumentHandlers.handleJigDocuments()

    val resultLog = handleResultList.asScala
      .filter(_.success)
      .map { handleResult => handleResult.jigDocument + " : " + handleResult.outputFilePathsText() }.mkString("\n")

    println(
      s"-- Output Complete ${System.currentTimeMillis - startTime} ms -------------------------------------------\n${resultLog}\n------------------------------------------------------------"
    )
  }

}
