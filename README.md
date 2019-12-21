# sbt-jig

An sbt AutoPlugin

## Usage

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

