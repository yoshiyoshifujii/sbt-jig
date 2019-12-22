libraryDependencies += "org.scala-sbt" %% "scripted-plugin" % sbtVersion.value
addSbtPlugin("org.scalameta"     % "sbt-scalafmt" % "2.2.1")
addSbtPlugin("org.xerial.sbt"    % "sbt-sonatype" % "3.8.1")
addSbtPlugin("com.github.gseitz" % "sbt-release"  % "1.0.12")
