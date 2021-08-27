import xerial.sbt.Sonatype.GitHubHosting

ThisBuild / sonatypeProfileName    := organization.value
ThisBuild / sonatypeProjectHosting := Some(GitHubHosting("yoshiyoshifujii", "sbt-jig", "yoshiyoshifujii@gmail.com"))
ThisBuild / publishTo              := sonatypePublishToBundle.value
