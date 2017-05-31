package com.github.pedrovgs

import org.apache.spark.{SparkConf, SparkContext}

trait SparkApp {

  implicit val sparkContext: SparkContext = {
    val config = new SparkConf()
      .setAppName("SparkPlayground")
      .setMaster("local[*]")
    SparkContext.getOrCreate(config)
  }

}
