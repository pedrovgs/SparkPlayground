package com.github.pedrovgs.sparkplayground.exercise10

import com.github.pedrovgs.sparkplayground.{Resources, SparkApp}
import org.apache.spark.streaming.dstream.DStream

object Logs extends SparkApp with Resources {

  private lazy val logTraces: DStream[String] = {
    val logsPath = getFilePath("/exercise10/logs.txt")
    streamingContext.textFileStream(logsPath)
  }

  def printGetRequests(): Unit = {
    logTraces.filter(_.contains("GET"))
      .foreachRDD(rdd => {
        pprint.pprintln("GET request received")
        pprint.pprintln(rdd.collect())
      })
  }

  def printRequestsDate(): Unit = {
    logTraces.map(line => {
      val start = line.indexOf("[") + 1
      val stop = line.indexOf("]") - 1
      line.substring(start, stop)
    })
      .foreachRDD(rdd => {
        pprint.pprintln("GET request received")
        pprint.pprintln(rdd.collect())
      })
  }

  printGetRequests()
  printRequestsDate()

  streamingContext.start()
  streamingContext.awaitTermination()
  streamingContext.stop()
}
