# sbt-jig

![Scala CI](https://github.com/yoshiyoshifujii/sbt-jig/workflows/Scala%20CI/badge.svg)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.yoshiyoshifujii/sbt-jig/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.yoshiyoshifujii/sbt-jig)
[![Scala Steward badge](https://img.shields.io/badge/Scala_Steward-helping-blue.svg?style=flat&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA4AAAAQCAMAAAARSr4IAAAAVFBMVEUAAACHjojlOy5NWlrKzcYRKjGFjIbp293YycuLa3pYY2LSqql4f3pCUFTgSjNodYRmcXUsPD/NTTbjRS+2jomhgnzNc223cGvZS0HaSD0XLjbaSjElhIr+AAAAAXRSTlMAQObYZgAAAHlJREFUCNdNyosOwyAIhWHAQS1Vt7a77/3fcxxdmv0xwmckutAR1nkm4ggbyEcg/wWmlGLDAA3oL50xi6fk5ffZ3E2E3QfZDCcCN2YtbEWZt+Drc6u6rlqv7Uk0LdKqqr5rk2UCRXOk0vmQKGfc94nOJyQjouF9H/wCc9gECEYfONoAAAAASUVORK5CYII=)](https://scala-steward.org)

An sbt AutoPlugin

## Usage

- [tutorial](https://github.com/yoshiyoshifujii/sbt-jig-tutorial)

This plugin requires sbt 1.0.0+

project/plugins.sbt

```sbt
resolvers += Resolver.jcenterRepo

addSbtPlugin("com.github.yoshiyoshifujii" % "sbt-jig" % <version>)
```

Run sbt and jigReports

```shell script
$ sbt
> jigReports
```

### Testing

Run `test` for regular unit tests.

Run `scripted` for [sbt script tests](http://www.scala-sbt.org/1.x/docs/Testing-sbt-plugins.html).
