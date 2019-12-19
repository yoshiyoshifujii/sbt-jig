lazy val root = (project in file("."))
    .enablePlugins(ScriptedPlugin)
    .settings(
      name := """sbt-jig""",
      organization := "org.dddjava.jig",
      resolvers += Resolver.jcenterRepo,
      libraryDependencies ++= Seq(
        "org.scalatest" %% "scalatest" % "3.0.1" % Test,
        "org.dddjava.jig" % "jig-core" % "2019.12.2"
      )
    )
    .settings(
      sbtPlugin := true,
      bintrayPackageLabels := Seq("sbt","plugin"),
      bintrayVcsUrl := Some("""git@github.com:org.dddjava/sbt-jig.git"""),
      scriptedLaunchOpts ++=
        Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
    )
