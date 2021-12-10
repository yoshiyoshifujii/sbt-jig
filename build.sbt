lazy val root = (project in file("."))
  .enablePlugins(ScriptedPlugin)
  .settings(
    name                 := """sbt-jig""",
    organization         := "com.github.yoshiyoshifujii",
    organizationHomepage := Some(url("https://github.com/yoshiyoshifujii/sbt-jig")),
    resolvers += Resolver.jcenterRepo,
    libraryDependencies ++= Seq(
      "org.scalatest"  %% "scalatest" % "3.2.10" % Test,
      "org.dddjava.jig" % "jig-core"  % "2021.12.1" excludeAll (
        ExclusionRule("org.apache.poi", "poi"),
        ExclusionRule("org.apache.poi", "poi-ooxml")
      ),
      "org.scalameta" %% "scalameta" % "4.4.31",
      "org.apache.poi" % "poi"       % "4.1.2",
      "org.apache.poi" % "poi-ooxml" % "4.1.2"
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
