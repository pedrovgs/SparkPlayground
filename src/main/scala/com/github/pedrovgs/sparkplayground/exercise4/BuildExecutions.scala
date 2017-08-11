package com.github.pedrovgs.sparkplayground.exercise4

import com.github.pedrovgs.sparkplayground.{Resources, SparkApp}
import org.apache.spark.rdd.RDD

import scala.collection.Map
import scala.concurrent.duration._
import scala.language.postfixOps

object BuildExecutions extends SparkApp with Resources {

  def firstFiveExecutionTimesGroupedByName(): Map[String, Long] = ???

  def firstFiveTasksExecutionTimesInMinutes(): Map[String, Long] = ???

  def listOfTasks(): Array[String] = ???

  def slowestTask(): (String, Long) = ???

  def fastestTask(): (String, Long) = ???

  def findFirstFiveTasksNamesSortedByName(): Array[String] = ???

  def findExecutionTasksGreaterThanOneMinute(): Map[String, Long] = ???

  def totalExecutionTime(): Double = ???

}
