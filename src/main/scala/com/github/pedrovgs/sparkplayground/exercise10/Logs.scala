package com.github.pedrovgs.sparkplayground.exercise10

import com.github.pedrovgs.sparkplayground.{Resources, SparkApp}
import org.apache.spark.streaming.dstream.DStream
import scala.concurrent.duration._

object Logs extends SparkApp with Resources {

  private val streamingTimeout: Long = (10 seconds).toMillis

  private lazy val logTraces: DStream[String] = {
    val logsPath = getFilePath("/exercise10/logs.txt")
    streamingContext.textFileStream(logsPath)
  }

  def printGetRequests(): Unit = {
    logTraces
      .filter(_.contains("GET"))
      .foreachRDD(rdd => {
        pprint.pprintln("GET request received:")
        pprint.pprintln(rdd.collect())
      })
  }

  def printRequestsDate(): Unit = {
    logTraces
      .map(line => {
        val start = line.indexOf("[") + 1
        val stop  = line.indexOf("]") - 1
        line.substring(start, stop)
      })
      .foreachRDD(rdd => {
        pprint.pprintln("Requests date:")
        pprint.pprintln(rdd.collect())
      })
  }

  def printResponseCodes(): Unit = {
    logTraces
      .map(line => {
        val start = line.lastIndexOf("\"") + 2
        val stop  = line.lastIndexOf(" ") - 1
        line.substring(start, stop)
      })
      .foreachRDD(rdd => {
        pprint.pprintln("Response codes:")
        pprint.pprintln(rdd.collect())
      })
  }

  printGetRequests()
  printRequestsDate()

  streamingContext.start()
  streamingContext.awaitTerminationOrTimeout(streamingTimeout)
  streamingContext.stop()
}
