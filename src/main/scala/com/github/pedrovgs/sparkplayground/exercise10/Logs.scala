package com.github.pedrovgs.sparkplayground.exercise10

import com.github.pedrovgs.sparkplayground.{Resources, SparkApp}

import scala.concurrent.duration._

object Logs extends SparkApp with Resources {

  private val streamingTimeout: Long = (100 seconds).toMillis

  def printGetRequests(): Unit = ???

  def printRequestsDate(): Unit = ???

  def printResponseCodes(): Unit = ???

  printGetRequests()
  printRequestsDate()
  printResponseCodes()

  streamingContext.start()
  streamingContext.awaitTerminationOrTimeout(streamingTimeout)
  streamingContext.stop()
}
