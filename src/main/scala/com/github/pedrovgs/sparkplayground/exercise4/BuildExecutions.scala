package com.github.pedrovgs.sparkplayground.exercise4

import com.github.pedrovgs.{Resources, SparkApp}
import org.apache.spark.rdd.RDD

import scala.concurrent.duration._
import scala.collection.Map
import scala.language.postfixOps

object BuildExecutions extends App with SparkApp with Resources {

  private lazy val executionTimes: RDD[(String, Long)] = sparkContext
    .textFile(getFilePath("/exercise4/buildExecutionTimes.txt"))
    .map(lines => lines.split(","))
    .map(parts => (parts(0), parts(1).toLong))

  private lazy val reducedExecutionTimes: RDD[(String, Long)] =
    executionTimes.reduceByKey((x, y) => x + y).persist()

  def firstFiveExecutionTimesGroupedByName(): Map[String, Long] =
    reducedExecutionTimes.take(5).toMap[String, Long]

  def firstFiveTasksExecutionTimesInMinutes(): Map[String, Long] =
    reducedExecutionTimes
      .map({ case (name, executionTime) => (name, toMilliseconds(executionTime)) })
      .take(5)
      .toMap[String, Long]

  def listOfTasks(): Array[String] = reducedExecutionTimes.keys.take(5)

  private def toMilliseconds(ms: Long): Long = (ms milliseconds).toMinutes

  pprint.pprintln(
    "This is the execution times of the first five task: " + firstFiveExecutionTimesGroupedByName())
  pprint.pprintln(
    "This is the execution times in minutes: " + firstFiveTasksExecutionTimesInMinutes())
  pprint.pprintln("This is the list of keys: " + listOfTasks())

}
