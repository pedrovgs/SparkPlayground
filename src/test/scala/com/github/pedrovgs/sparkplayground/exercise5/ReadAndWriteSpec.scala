package com.github.pedrovgs.sparkplayground.exercise5

import com.github.pedrovgs.sparkplayground.exercise5.ReadAndWrite._
import com.holdenkarau.spark.testing.SharedSparkContext
import org.scalatest.{FlatSpec, Matchers}

class ReadAndWriteSpec extends FlatSpec with Matchers with SharedSparkContext {

  "ReadAndWrite" should "read a plain text file, capitalize it and save it again into the outputs folder" in {
    readAndWriteText()
    sparkContext
      .textFile("./outputs/capitalizedTextFile.txt")
      .collect() shouldBe List("Sometimes",
                               "It",
                               "Is",
                               "The",
                               "People",
                               "No",
                               "One",
                               "Imagines",
                               "Anything",
                               "Of",
                               "Who",
                               "Do",
                               "The",
                               "Things",
                               "No",
                               "One",
                               "Can",
                               "Imagine.")
  }

  it should "read a JSON file, sort by last name and save it again" in {
    readAndWriteJson()
    sparkContext
      .textFile("./outputs/firstUser.json")
      .first() shouldBe "{\"name\":{\"title\":\"mr\",\"first\":\"emre\",\"last\":\"adal\"}}"
  }

  it should "read a CSV file, filter values associated to GameBoy sales and save it again" in {
    readAndWriteCSV()
    val gameBoySales = sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .option("inferSchema", "true")
      .load("./outputs/gameBoyGamesSales.csv")
    gameBoySales
      .select("Platform")
      .collect()
      .exists(row => row.getAs[String]("Platform") != "GB") shouldBe false
    gameBoySales.count() shouldBe 98
  }
}
