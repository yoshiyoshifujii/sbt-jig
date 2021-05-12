package org.dddjava.jig.sbt

import org.dddjava.jig.domain.model.documents.documentformat.{ JigDiagramFormat, JigDocument }
import sbt._

trait JigKeys {

  lazy val jig        = taskKey[Unit]("Java Instant-document Gazer")
  lazy val jigReports = taskKey[Unit]("Generates JIG documentation for the main source code.")

  lazy val jigDocuments           = settingKey[Seq[JigDocument]]("出力対象")
  lazy val jigOutputDirectoryText = settingKey[String]("出力ディレクトリ")
  lazy val jigOmitPrefix          = settingKey[String]("出力時に省略するプレフィックス")
  lazy val jigPatternDomain       = settingKey[String]("ドメインクラスのパターン（正規表現）")
  lazy val jigOutputDiagramFormat = settingKey[JigDiagramFormat]("図のフォーマット（SVG, PNG, DOT）")
  lazy val jigLinkPrefix          = settingKey[String]("ダイアグラムのリンクのprefix: ソースコードにリンクする際に使用（SVG限定）")

  lazy val jigProjectPath        = settingKey[String]("情報を読み取るルートディレクトリ")
  lazy val jigDirectoryClasses   = settingKey[String]("情報を読み取るディレクトリ（sbt準拠）")
  lazy val jigDirectoryResources = settingKey[String]("情報を読み取るディレクトリ（sbt準拠）")
  lazy val jigDirectorySources   = settingKey[String]("情報を読み取るディレクトリ（sbt準拠）")

}
