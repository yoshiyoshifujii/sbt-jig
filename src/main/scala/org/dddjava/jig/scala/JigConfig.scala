package org.dddjava.jig.scala

import org.dddjava.jig.domain.model.documents.documentformat.{ JigDiagramFormat, JigDocument }
import org.dddjava.jig.domain.model.sources.file.SourcePaths
import org.dddjava.jig.domain.model.sources.file.binary.BinarySourcePaths
import org.dddjava.jig.domain.model.sources.file.text.CodeSourcePaths
import org.dddjava.jig.domain.model.sources.jigreader.AdditionalTextSourceReader
import org.dddjava.jig.infrastructure.ScalametaAliasReader
import org.dddjava.jig.infrastructure.configuration.{ Configuration, JigProperties }

import java.io.{ IOException, UncheckedIOException }
import java.nio.file.{ Files, Path, Paths }
import java.util.StringJoiner
import scala.collection.JavaConverters.*

case class JigConfig(
    private val documents: Seq[JigDocument],
    private val modelPattern: String,
    private val outputDirectoryText: String,
    private val diagramFormat: JigDiagramFormat,
    private val omitPrefix: String,
    private val projectPath: String,
    private val directoryClasses: String,
    private val directoryResources: String,
    private val directorySources: String
) {

  def propertiesText(): String =
    new StringJoiner("\n")
      .add("jig.document.types=" + documents)
      .add("jig.pattern.domain=" + modelPattern)
      .add("jig.output.directory=" + outputDirectory)
      .add("jig.output.diagram.format:svg=" + diagramFormat)
      .add("jig.omit.prefix=" + omitPrefix)
      .add("project.path=" + projectPath)
      .add("directory.classes=" + directoryClasses)
      .add("directory.resources=" + directoryResources)
      .add("directory.sources=" + directorySources)
      .toString

  def outputDirectory(): Path = Paths.get(outputDirectoryText)

  def rawSourceLocations(): SourcePaths =
    try {
      val projectRoot = Paths.get(projectPath)
      val binaryCollector = new DirectoryCollector(path =>
        path.endsWith(directoryClasses) || path.endsWith(directoryResources)
      )
      Files.walkFileTree(projectRoot, binaryCollector)
      val binarySourcePaths = binaryCollector.listPath

      val sourceCollector = new DirectoryCollector(_.endsWith(directorySources))
      Files.walkFileTree(projectRoot, sourceCollector)
      val textSourcesPaths = sourceCollector.listPath
      new SourcePaths(
        new BinarySourcePaths(binarySourcePaths.asJava),
        new CodeSourcePaths(textSourcesPaths.asJava)
      )
    } catch {
      case e: IOException =>
        throw new UncheckedIOException(e)
    }

  def configuration(): Configuration =
    new Configuration(
      new JigProperties(
        documents.asJava,
        modelPattern,
        outputDirectory(),
        diagramFormat
      ),
      new AdditionalTextSourceReader(new ScalametaAliasReader())
    )

}
