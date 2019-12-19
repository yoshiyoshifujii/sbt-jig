name := """sbt-jig"""
organization := "org.dddjava"
version := "0.1-SNAPSHOT"

sbtPlugin := true

// ScalaTest
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

bintrayPackageLabels := Seq("sbt","plugin")
bintrayVcsUrl := Some("""git@github.com:org.dddjava/sbt-jig.git""")

initialCommands in console := """import org.dddjava.sbt._"""

enablePlugins(ScriptedPlugin)
// set up 'scripted; sbt plugin for testing sbt plugins
scriptedLaunchOpts ++=
  Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
