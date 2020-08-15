package org.dddjava.jig.sbt

import sbt._

trait JigKeys {

  lazy val jig        = taskKey[Unit]("Java Instant-document Gazer")
  lazy val jigReports = taskKey[Unit]("Generates JIG documentation for the main source code.")

  lazy val jigDocumentTypeText      = settingKey[String]("出力対象（カンマで複数指定）")
  lazy val jigOutputDirectoryText   = settingKey[String]("出力ディレクトリ")
  lazy val jigOutputOmitPrefix      = settingKey[String]("出力時に省略するプレフィックス")
  lazy val jigModelPattern          = settingKey[String]("ビジネスルールと認識するパターン")
  lazy val jigApplicationPattern    = settingKey[String]("アーキテクチャの識別パターン")
  lazy val jigInfrastructurePattern = settingKey[String]("アーキテクチャの識別パターン")
  lazy val jigPresentationPattern   = settingKey[String]("アーキテクチャの識別パターン")
  lazy val jigProjectPath           = settingKey[String]("情報を読み取るルートディレクトリ")
  lazy val jigDirectoryClasses      = settingKey[String]("情報を読み取るディレクトリ（sbt準拠）")
  lazy val jigDirectoryResources    = settingKey[String]("情報を読み取るディレクトリ（sbt準拠）")
  lazy val jigDirectorySources      = settingKey[String]("情報を読み取るディレクトリ（sbt準拠）")
  lazy val jigLinkPrefix            = settingKey[String]("ダイアグラムのリンクのprefix: ソースコードにリンクする際に使用（SVG限定）")

}
