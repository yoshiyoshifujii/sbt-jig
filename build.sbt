lazy val root = (project in file("."))
  .enablePlugins(ScriptedPlugin)
  .settings(
    name                 := """sbt-jig""",
    organization         := "com.github.yoshiyoshifujii",
    organizationHomepage := Some(url("https://github.com/yoshiyoshifujii/sbt-jig")),
    resolvers += Resolver.jcenterRepo,
    libraryDependencies ++= Seq(
      "org.scalatest"  %% "scalatest" % "3.2.11" % Test,
      "org.dddjava.jig" % "jig-core"  % "2022.3.2.1" excludeAll (
        ExclusionRule("org.apache.logging.log4j")
      ),
      "org.scalameta"           %% "scalameta" % "4.5.0",
      "org.apache.logging.log4j" % "log4j-bom" % "2.17.2"
    )
  )
  .settings(
    sbtPlugin := true,
    scriptedLaunchOpts ++=
      Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
  )
  .settings(
    ThisBuild / scalafmtOnCompile := true
  )
