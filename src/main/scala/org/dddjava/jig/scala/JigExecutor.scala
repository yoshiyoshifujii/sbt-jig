package org.dddjava.jig.scala

import scala.collection.JavaConverters._
import scala.jdk.OptionConverters._

object JigExecutor {

  def jigReports(cliConfig: JigConfig): Unit = {

    val configuration = cliConfig.configuration()

    println(
      s"-- configuration -------------------------------------------\n${cliConfig.propertiesText()}\n------------------------------------------------------------"
    )

    val startTime = System.currentTimeMillis

    val jigSourceReadService = configuration.sourceReader()
    val jigDocumentHandlers  = configuration.documentGenerator()

    val sourcePaths    = cliConfig.rawSourceLocations()
    val maybeJigSource = jigSourceReadService.readPathSource(sourcePaths).toScala

    maybeJigSource match {
      case None =>
        println(
          s"-- Source is nothing."
        )
      case Some(jigSource) =>
        val handleResultList = jigDocumentHandlers.generateDocuments(jigSource)

        val resultLog = handleResultList.asScala
          .filter(_.success)
          .map { handleResult => handleResult.jigDocument + " : " + handleResult.outputFilePathsText() }.mkString("\n")

        println(
          s"-- Output Complete ${System.currentTimeMillis - startTime} ms -------------------------------------------\n${resultLog}\n------------------------------------------------------------"
        )
    }
  }
}
