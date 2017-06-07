package com.github.pedrovgs

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import org.apache.spark.SparkContext
import org.apache.spark.sql.{SQLContext, SparkSession}

trait SparkApp {

  private val sparkSession: SparkSession = SparkSession
    .builder()
    .appName("SparkPlayground")
    .master("local[*]")
    .getOrCreate()
  val sparkContext: SparkContext = sparkSession.sparkContext
  val sqlContext: SQLContext     = sparkSession.sqlContext

  val objectMapper: ObjectMapper = {
    val mapper = new ObjectMapper() with ScalaObjectMapper
    mapper.registerModule(DefaultScalaModule)
  }

}
