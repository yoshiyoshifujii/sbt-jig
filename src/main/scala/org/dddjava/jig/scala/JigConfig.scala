package org.dddjava.jig.scala

import java.io.{ IOException, UncheckedIOException }
import java.nio.file.{ Files, Path, Paths }
import java.util.StringJoiner

import org.dddjava.jig.domain.model.jigdocument.documentformat.JigDocument
import org.dddjava.jig.domain.model.jigdocument.stationery.LinkPrefix
import org.dddjava.jig.domain.model.jigsource.file.SourcePaths
import org.dddjava.jig.domain.model.jigsource.file.binary.BinarySourcePaths
import org.dddjava.jig.domain.model.jigsource.file.text.CodeSourcePaths
import org.dddjava.jig.domain.model.jigsource.jigloader.SourceCodeAliasReader
import org.dddjava.jig.infrastructure.ScalametaAliasReader
import org.dddjava.jig.infrastructure.configuration.{ Configuration, JigProperties, OutputOmitPrefix }
import org.dddjava.jig.infrastructure.javaparser.JavaparserAliasReader

import scala.collection.JavaConverters._

case class JigConfig(
    private val documentTypeText: String,
    private val outputDirectoryText: String,
    private val outputOmitPrefix: String,
    private val modelPattern: String,
    private val applicationPattern: String,
    private val infrastructurePattern: String,
    private val presentationPattern: String,
    private val projectPath: String,
    private val directoryClasses: String,
    private val directoryResources: String,
    private val directorySources: String,
    private val linkPrefix: String
) {

  def propertiesText(): String =
    new StringJoiner("\n")
      .add("documentType=" + documentTypeText)
      .add("outputDirectory=" + outputDirectory)
      .add("output.omit.prefix=" + outputOmitPrefix)
      .add("jig.model.pattern=" + modelPattern)
      .add("jig.infrastructure.pattern=" + infrastructurePattern)
      .add("project.path=" + projectPath)
      .add("directory.classes=" + directoryClasses)
      .add("directory.resources=" + directoryResources)
      .add("directory.sources=" + directorySources)
      .add("linkPrefix=" + linkPrefix)
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
        modelPattern,
        applicationPattern,
        infrastructurePattern,
        presentationPattern,
        new OutputOmitPrefix(outputOmitPrefix),
        new LinkPrefix(linkPrefix)
      ),
      new SourceCodeAliasReader(new JavaparserAliasReader(), new ScalametaAliasReader())
    )

  def jigDocuments(): Seq[JigDocument] =
    if (documentTypeText.isEmpty)
      JigDocument.canonical().asScala
    else
      JigDocument.resolve(documentTypeText).asScala

}
