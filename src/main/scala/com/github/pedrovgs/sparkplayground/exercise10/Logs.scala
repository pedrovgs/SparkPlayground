package com.github.pedrovgs.sparkplayground.exercise10

import com.github.pedrovgs.sparkplayground.{Resources, SparkApp}
import org.apache.spark.streaming.dstream.DStream

object Logs extends SparkApp with Resources {

  private def logTraces(): DStream[String] = {
    val logsPath = getFilePath("/exercise10/logs.txt")
    streamingContext.textFileStream(logsPath)
  }

  def numberOfGetRequests(): Unit = {
    logTraces().filter(_.contains("GET"))
      .foreachRDD(rdd => pprint.pprintln(rdd.collect()))
  }

  pprint.pprintln("GET requests:")
  numberOfGetRequests()

  streamingContext.start()
  streamingContext.awaitTermination()
  streamingContext.stop()
}
