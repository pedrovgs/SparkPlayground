package com.github.pedrovgs.sparkplayground.exercise4

import com.github.pedrovgs.{Resources, SparkApp}
import org.apache.spark.rdd.RDD

import scala.collection.Map

object BuildExecutions extends App with SparkApp with Resources {

  private lazy val executionTimes: RDD[(String, Long)] = sparkContext
    .textFile(getFilePath("/exercise4/buildExecutionTimes.txt"))
    .map(lines => lines.split(","))
    .map(parts => (parts(0), parts(1).toLong))

  def firstFiveExecutionTimesGroupedByName(): Map[String, Long] =
    executionTimes.reduceByKey((x, y) => x + y).take(5).toMap[String, Long]

  def tasksExecutionTimes(): Map[String, Long] = firstFiveExecutionTimesGroupedByName()

  pprint.pprintln("This is the execution times of the first five task: " + tasksExecutionTimes())

}
