package com.github.pedrovgs.sparkplayground.exercise5

import java.io.File

import com.github.pedrovgs.{Resources, SparkApp}
import org.apache.commons.io.FileUtils
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.DataFrame

import scala.util.Try

object ReadAndWrite extends App with SparkApp with Resources {

  def readAndWriteText(): Unit = {
    val outputFile = "./outputs/capitalizedTextFile.txt"
    delete(outputFile)
    val capitalizedText = readAndCapitalizeTextFile()
    capitalizedText.saveAsTextFile(outputFile)
  }

  def readAndWriteJson(): Unit = {
    val user = readFirstUserSortedByLastName()
    writeUserAsJson(user)
  }

  def readAndWriteCSV(): Unit = {
    val gameBoySales = readGameBoySales()
    writeAsCsv(gameBoySales)
  }

  private def writeAsCsv(gameBoySales: DataFrame) = {
    val outputFile = "./outputs/gameBoyGamesSales.csv"
    delete(outputFile)
    gameBoySales.write
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .save(outputFile)
  }

  private def readGameBoySales(): DataFrame = {
    sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .option("inferSchema", "true")
      .load(getFilePath("/exercise5/videoGamesSales.csv"))
      .filter(row => row.getAs[String]("Platform") == "GB")
  }

  private def readAndCapitalizeTextFile(): RDD[String] = {
    val resourceFile = getFilePath("/exercise5/textFile.txt")
    sparkContext.textFile(resourceFile).flatMap(_.split(" ")).map(line => line.capitalize)
  }

  private def readFirstUserSortedByLastName(): RDD[User] = {
    sparkContext
      .textFile(getFilePath("/exercise5/users.json"))
      .flatMap(line =>
        Try {
          objectMapper.readValue(line, classOf[User])
        }.toOption)
      .sortBy(_.name.last)
  }

  private def writeUserAsJson(userRDD: RDD[User]): Unit = {
    val outputFile = getOutputFilePath("/firstUser.json")
    delete(outputFile)
    userRDD
      .map(user => {
        objectMapper.writeValueAsString(user)
      })
      .saveAsTextFile(outputFile)
  }

  private def delete(path: String): Unit = {
    FileUtils.deleteDirectory(new File(path))
  }

}
