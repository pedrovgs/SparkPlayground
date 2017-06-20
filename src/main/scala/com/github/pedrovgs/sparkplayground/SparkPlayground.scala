package com.github.pedrovgs.sparkplayground

import com.github.pedrovgs.sparkplayground.exercise1.ElQuijote
import com.github.pedrovgs.sparkplayground.exercise2.NumericalSeries
import com.github.pedrovgs.sparkplayground.exercise3.Sets
import com.github.pedrovgs.sparkplayground.exercise4.BuildExecutions
import com.github.pedrovgs.sparkplayground.exercise5.ReadAndWrite
import com.github.pedrovgs.sparkplayground.exercise6.Movies

object SparkPlayground extends App with SparkApp {

  pprint.pprintln("Spark app name: " + sparkContext.appName)
  pprint.pprintln("Spark app id: " + sparkContext.applicationId)
  pprint.pprintln("Spark uploaded to Spark executors:")
  pprint.pprintln(sparkContext.listFiles())

  executeExercise(ElQuijote)
  executeExercise(NumericalSeries)
  executeExercise(Sets)
  executeExercise(BuildExecutions)
  executeExercise(ReadAndWrite)
  executeExercise(Movies)

  private def executeExercise(app: App): Unit = {
    pprint.pprintln("-----------------")
    pprint.pprintln("Executing exercise " + app.getClass.getSimpleName)
    app.main(Array())
    pprint.pprintln("Exercise finished")
    pprint.pprintln("-----------------")
  }

}
