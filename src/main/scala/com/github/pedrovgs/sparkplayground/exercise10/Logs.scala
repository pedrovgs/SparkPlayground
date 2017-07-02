package com.github.pedrovgs.sparkplayground.exercise10

import com.github.pedrovgs.sparkplayground.{Resources, SparkApp}
import org.apache.spark.streaming.dstream.DStream
import scala.concurrent.duration._

object Logs extends SparkApp with Resources {

  private val streamingTimeout: Long = (100 seconds).toMillis

  private def logsPath: String = getFilePath("/exercise10/")

  private lazy val logTraces: DStream[String] = {
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
        val stop  = line.indexOf("]")
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
        val stop  = line.lastIndexOf(" ")
        line.substring(start, stop)
      })
      .foreachRDD(rdd => {
        pprint.pprintln("Response codes:")
        pprint.pprintln(rdd.collect())
      })
  }

  pprint.pprintln("Listening changes at " + logsPath)
  printGetRequests()
  printRequestsDate()
  printResponseCodes()

  streamingContext.start()
  streamingContext.awaitTerminationOrTimeout(streamingTimeout)
  streamingContext.stop()
}
