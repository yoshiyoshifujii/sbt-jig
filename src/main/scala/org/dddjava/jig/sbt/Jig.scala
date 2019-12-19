package org.dddjava.jig.sbt

import sbt._
import Def.Initialize
import org.dddjava.jig.scala.JigExecutor

object Jig {

  def jigReportsTask(key: TaskKey[Unit]): Initialize[Task[Unit]] =
    Def.task {
      JigExecutor.jigReports()
    }

}
