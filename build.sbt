lazy val root = (project in file("."))
  .enablePlugins(ScriptedPlugin)
  .settings(
    name := """sbt-jig""",
    organization := "com.github.yoshiyoshifujii",
    organizationHomepage := Some(url("https://github.com/yoshiyoshifujii/sbt-jig")),
    resolvers += Resolver.jcenterRepo,
    libraryDependencies ++= Seq(
      "org.scalatest"  %% "scalatest" % "3.2.8" % Test,
      "org.dddjava.jig" % "jig-core"  % "2021.5.1",
      "org.scalameta"  %% "scalameta" % "4.4.16"
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
