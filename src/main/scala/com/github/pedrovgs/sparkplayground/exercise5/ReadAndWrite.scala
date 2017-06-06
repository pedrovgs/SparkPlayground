package com.github.pedrovgs.sparkplayground.exercise5

import java.io.File

import com.github.pedrovgs.{Resources, SparkApp}
import org.apache.commons.io.FileUtils
import org.apache.spark.rdd.RDD

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

  private def readAndCapitalizeTextFile(): RDD[String] = {
    val resourceFile = getFilePath("/exercise5/textFile.txt")
    sparkContext.textFile(resourceFile).flatMap(_.split(" ")).map(line => line.capitalize)
  }

  private def readFirstUserSortedByLastName(): User = {
    sparkContext
      .textFile(getFilePath("/exercise5/users.json"))
      .flatMap(line =>
        Try {
          objectMapper.readValue(line, classOf[User])
        }.toOption)
      .sortBy(_.name.last)
      .first()
  }

  private def writeUserAsJson(user: User): Unit = {
    val outputFile = getOutputFilePath("/firstUser.json")
    delete(outputFile)
    sparkContext
      .parallelize(List(user))
      .map(user => {
        objectMapper.writeValueAsString(user)
      })
      .saveAsTextFile(outputFile)
  }

  private def delete(path: String): Unit = {
    FileUtils.deleteDirectory(new File(path))
  }

  pprint.pprintln(
    "This is the first user if we sort them by their last name: " + readFirstUserSortedByLastName())
}
