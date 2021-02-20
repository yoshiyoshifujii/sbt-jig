ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/yoshiyoshifujii/sbt-jig"),
    "scm:git@github.com:yoshiyoshifujii/sbt-jig.git"
  )
)
ThisBuild / developers := List(
  Developer(
    id = "yoshiyoshifujii",
    name = "FUJII Yoshitaka",
    email = "yoshiyoshifujii@gmail.com",
    url = url("https://github.com/yoshiyoshifujii")
  )
)

ThisBuild / description := "sbt plugin of Java Instant-document Gazer"
ThisBuild / licenses := List("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt"))
ThisBuild / homepage := organizationHomepage.value
