publishMavenStyle := true

publishTo := sonatypePublishTo.value

publishArtifact in Test := false

pomIncludeRepository := { _ =>
  false
}

sonatypeProfileName := "com.github.yoshiyoshifujii"

pomExtra := (<url>https://github.com/yoshiyoshifujii/sbt-jig</url>
  <licenses>
    <license>
      <name>Apache 2</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:yoshiyoshifujii/sbt-jig.git</url>
    <connection>scm:git:git@github.com:yoshiyoshifujii/sbt-jig.git</connection>
  </scm>
  <developers>
    <developer>
      <id>yoshiyoshifujii</id>
      <name>Yoshitaka Fujii</name>
      <url>https://github.com/yoshiyoshifujii</url>
    </developer>
  </developers>)
