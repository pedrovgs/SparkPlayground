package com.github.pedrovgs.sparkplayground

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import org.apache.spark.SparkContext
import org.apache.spark.sql.{SQLContext, SparkSession}

trait SparkApp {

  private lazy val sparkSession: SparkSession = SparkSession
    .builder()
    .appName("SparkPlayground")
    .getOrCreate()
  lazy val sparkContext: SparkContext = sparkSession.sparkContext
  lazy val sqlContext: SQLContext     = sparkSession.sqlContext

  lazy val objectMapper: ObjectMapper = {
    val mapper = new ObjectMapper() with ScalaObjectMapper
    mapper.registerModule(DefaultScalaModule)
  }

}
