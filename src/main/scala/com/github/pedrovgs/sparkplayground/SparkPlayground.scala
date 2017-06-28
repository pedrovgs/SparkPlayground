package com.github.pedrovgs.sparkplayground

import com.github.pedrovgs.sparkplayground.exercise2.NumericalSeries
import com.github.pedrovgs.sparkplayground.exercise3.Sets
import com.github.pedrovgs.sparkplayground.exercise4.BuildExecutions
import com.github.pedrovgs.sparkplayground.exercise5.ReadAndWrite
import com.github.pedrovgs.sparkplayground.exercise6.Movies
import com.github.pedrovgs.sparkplayground.exercise8.Kryo
import com.github.pedrovgs.sparkplayground.exercise9.Fifa

object SparkPlayground extends SparkApp {

  pprint.pprintln("Spark app name: " + sparkContext.appName)
  pprint.pprintln("Spark app id: " + sparkContext.applicationId)

  val files = sparkContext.listFiles()
  if (files.nonEmpty) {
    pprint.pprintln("Spark uploaded to Spark executors:")
    pprint.pprintln(files)
  }

  if (args != null && args.nonEmpty) {
    pprint.pprintln("This is this full list of params used to execute the app:")
    pprint.pprintln(args)
  }

  executeExercise(NumericalSeries)
  executeExercise(Sets)
  executeExercise(BuildExecutions)
  executeExercise(ReadAndWrite)
  executeExercise(Movies)
  executeExercise(Kryo)
  executeExercise(Fifa)

  private def executeExercise(app: App): Unit = {
    pprint.pprintln("-----------------")
    pprint.pprintln("Executing exercise " + app.getClass.getSimpleName)
    app.main(Array())
    pprint.pprintln("Exercise finished")
    pprint.pprintln("-----------------")
  }

}
