lazy val root = (project in file("."))
  .enablePlugins(ScriptedPlugin)
  .settings(
    name := """sbt-jig""",
    organization := "com.github.yoshiyoshifujii",
    resolvers += Resolver.jcenterRepo,
    libraryDependencies ++= Seq(
      "org.scalatest"  %% "scalatest" % "3.2.5" % Test,
      "org.dddjava.jig" % "jig-core"  % "2021.2.2",
      "org.scalameta"  %% "scalameta" % "4.4.8"
    )
  )
  .settings(
    sbtPlugin := true,
    scriptedLaunchOpts ++=
      Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
  )
  .settings(
    licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html")),
    publishMavenStyle := false,
    bintrayRepository := "sbt-plugins",
    bintrayPackageLabels := Seq("sbt", "plugin"),
    bintrayVcsUrl := Some("""git@github.com:yoshiyoshifujii/sbt-jig.git""")
  )
  .settings(
    scalafmtOnCompile in ThisBuild := true
  )
