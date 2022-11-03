package org.dddjava.jig.application.service

import org.dddjava.jig.domain.model.parts.classes.`type`.TypeIdentifier
import org.dddjava.jig.domain.model.sources.file.binary.BinarySourcePaths
import org.dddjava.jig.domain.model.sources.file.text.CodeSourcePaths
import org.dddjava.jig.domain.model.sources.file.{ SourcePaths, Sources }
import org.dddjava.jig.domain.model.sources.jigreader.{ AdditionalTextSourceReader, TextSourceReader }
import org.dddjava.jig.infrastructure.ScalametaAliasReader
import org.dddjava.jig.infrastructure.filesystem.LocalFileSourceReader
import org.dddjava.jig.infrastructure.javaparser.JavaparserReader
import org.dddjava.jig.infrastructure.onmemoryrepository.{ OnMemoryCommentRepository, OnMemoryJigSourceRepository }
import org.scalatest.freespec.AnyFreeSpec
import stub.domain.model.ScalaMethodScaladocStub.ObjectInObject.ObjectInObjectInObject
import stub.domain.model.ScalaMethodScaladocStub.SealedTrait
import stub.domain.model.pkg1.PackageObjectTrait
import stub.domain.model.{ ScalaMethodScaladocStub, ScalaStub }

import java.net.URI
import java.nio.file.{ Path, Paths }
import java.util.Collections

class AliasServiceSpec extends AnyFreeSpec {

  "AliasService" - {
    lazy val additionalTextSourceReader = new AdditionalTextSourceReader(new ScalametaAliasReader())
    val javaParserReader                = new JavaparserReader(null)
    lazy val textSourceReader           = new TextSourceReader(javaParserReader, additionalTextSourceReader)
    lazy val onMemoryAliasRepository    = new OnMemoryCommentRepository()
    lazy val jigSourceRepository        = new OnMemoryJigSourceRepository(onMemoryAliasRepository)
    lazy val jigSourceReadService =
      new JigSourceReadService(jigSourceRepository, null, textSourceReader, null, null)
    lazy val sut: AliasService           = new AliasService(onMemoryAliasRepository)
    lazy val defaultPackageClassURI: URI = this.getClass.getResource("/DefaultPackageClass.class").toURI.resolve("./")
    lazy val getModuleRootPath: Path = {
      var path = Paths.get(defaultPackageClassURI).toAbsolutePath
      println(path)
      while (!path.endsWith("sbt-jig")) {
        path = path.getParent
        if (path == null) throw new RuntimeException("モジュール名変わった？")
      }
      path
    }
    lazy val getRawSourceLocations: SourcePaths =
      new SourcePaths(
        new BinarySourcePaths(Collections.singletonList(Paths.get(defaultPackageClassURI))),
        new CodeSourcePaths(
          Collections.singletonList(getModuleRootPath.resolve("src").resolve("test").resolve("scala"))
        )
      )
    lazy val getTestRawSource: Sources = {
      new LocalFileSourceReader().readSources(getRawSourceLocations)
    }

    "Scalaクラス別名取得" in {
      val sources = getTestRawSource
      jigSourceReadService.readTextSources(sources.textSources())

      val typeAlias1 = sut.typeAliasOf(new TypeIdentifier(classOf[ScalaStub]))
      assert(typeAlias1.asText() === "ScalaのクラスのDoc")

      val typeAlias2 = sut.typeAliasOf(new TypeIdentifier(classOf[ScalaMethodScaladocStub]))
      assert(typeAlias2.asText() === "ScalaのTraitのDoc")

      val typeAlias3 = sut.typeAliasOf(new TypeIdentifier("stub.domain.model.ScalaMethodScaladocStub$"))
      assert(typeAlias3.asText() === "ScalaのObjectのDoc")

      val typeAlias4 = sut.typeAliasOf(new TypeIdentifier(classOf[SealedTrait]))
      assert(typeAlias4.asText() === "Object内のTrait")

      val typeAlias5 = sut.typeAliasOf(new TypeIdentifier(classOf[ObjectInObjectInObject]))
      assert(typeAlias5.asText() === "Objectの中のObjectの中のObject")

      val typeAlias6 = sut.typeAliasOf(new TypeIdentifier(classOf[PackageObjectTrait]))
      assert(typeAlias6.asText() === "パッケージObjectのTrait")
    }

//    "Scalaメソッドの和名取得" in {
//      val source = getTestRawSource
//      sut.loadAliases(source.aliasSource())
//
//      val typeIdentifier = new TypeIdentifier(classOf[ScalaMethodScaladocStub])
//      val identifier1    = new MethodIdentifier(typeIdentifier, new MethodSignature("simpleMethod", Arguments.empty()))
//      val methodAlias1   = sut.methodAliasOf(identifier1)
//      assert(methodAlias1.asText() === "メソッドのドキュメント")
//    }

  }

}
