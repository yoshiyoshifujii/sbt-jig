lazy val root = (project in file("."))
  .enablePlugins(ScriptedPlugin)
  .settings(
    name := """sbt-jig""",
    organization := "com.github.yoshiyoshifujii",
    resolvers += Resolver.jcenterRepo,
    libraryDependencies ++= Seq(
      "org.scalatest"   %% "scalatest" % "3.1.2" % Test,
      "org.dddjava.jig" % "jig-core"   % "2020.5.5",
      "org.scalameta"   %% "scalameta" % "4.3.13"
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
    bintrayOrganization in bintray := None,
    bintrayPackageLabels := Seq("sbt", "plugin"),
    bintrayVcsUrl := Some("""git@github.com:yoshiyoshifujii/sbt-jig.git""")
  )
  .settings(
    scalafmtOnCompile in ThisBuild := true
  )
