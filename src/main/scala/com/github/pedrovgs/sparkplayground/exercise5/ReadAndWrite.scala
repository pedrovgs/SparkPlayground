package com.github.pedrovgs.sparkplayground.exercise5

import java.io.File

import com.github.pedrovgs.{Resources, SparkApp}
import org.apache.commons.io.FileUtils

import scala.util.Try

object ReadAndWrite extends App with SparkApp with Resources {

  def readAndWriteJson(): Unit = {
    val user = readFirstUserSortedByLastName()
    writeUserAsJson(user)
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
