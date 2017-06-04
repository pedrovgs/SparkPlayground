package com.github.pedrovgs.sparkplayground.exercise4

import com.github.pedrovgs.{Resources, SparkApp}
import org.apache.spark.rdd.RDD
import scala.collection.Map
import scala.concurrent.duration._
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

  def slowestTask(): (String, Long) = reducedExecutionTimes.sortBy(_._2, ascending = false).first()

  def fastestTask(): (String, Long) = reducedExecutionTimes.sortBy(_._2).first()

  def findFirstFiveTasksNamesSortedByName(): Array[String] = reducedExecutionTimes.keys.top(5)

  def findExecutionTasksGreaterThanOneMinute(): Map[String, Long] =
    reducedExecutionTimes
      .filter { case (_, executionTime) => executionTime > (1 hour).toNanos }
      .collectAsMap()

  def totalExecutionTime(): Double = reducedExecutionTimes.values.sum()

  private def toMilliseconds(ms: Long): Long = (ms milliseconds).toMinutes

  pprint.pprintln(
    "This is the execution times of the first five task: " + firstFiveExecutionTimesGroupedByName())
  pprint.pprintln(
    "This is the execution times in minutes: " + firstFiveTasksExecutionTimesInMinutes())
  pprint.pprintln("This is the list of keys: " + listOfTasks())
  pprint.pprintln("This is the slowest task: " + slowestTask())
  pprint.pprintln("This is the fastest task: " + fastestTask())
  pprint.pprintln(
    "This is the first five task names sorted ascending: " + findFirstFiveTasksNamesSortedByName)
  pprint.pprintln(
    "This is the list of build execution tasks greater than sixty minutes: " +
      findExecutionTasksGreaterThanOneMinute())
  pprint.pprintln("This is the total build execution time: " + totalExecutionTime())
}
