lazy val root = (project in file("."))
  .enablePlugins(ScriptedPlugin)
  .settings(
    name := """sbt-jig""",
    organization := "com.github.yoshiyoshifujii",
    resolvers += Resolver.jcenterRepo,
    libraryDependencies ++= Seq(
      "org.scalatest"   %% "scalatest" % "3.0.1" % Test,
      "org.dddjava.jig" % "jig-core"   % "2019.12.2"
    )
  )
  .settings(
    sbtPlugin := true,
    scriptedLaunchOpts ++=
      Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
  )
