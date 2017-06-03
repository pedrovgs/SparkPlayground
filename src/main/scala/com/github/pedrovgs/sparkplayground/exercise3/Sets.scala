package com.github.pedrovgs.sparkplayground.exercise3

import com.github.pedrovgs.SparkApp
import org.apache.spark.rdd.RDD

object Sets extends App with SparkApp {

  private lazy val frankensteinWords: RDD[String] = extractWords("/exercise3/frankenstein.txt")
  private lazy val dorianGrayWords: RDD[String] = extractWords(
    "/exercise3/the-picture-of-dorian-gray.txt")

  def findFirstFiveFrankensteinWords(): Array[String] = frankensteinWords.take(5)

  def findFirstFiveDorianGrayWords(): Array[String] = dorianGrayWords.take(5)

  private def extractWords(fileName: String): RDD[String] = {
    val filePath = getClass.getResource(fileName).getPath
    sparkContext.textFile(filePath).flatMap(_.split(" ")).filter(_.nonEmpty).persist()
  }

  pprint.pprintln(
    "This is the list of words you can find in Frankenstein: " + frankensteinWords
      .collect()
      .mkString(","))
  pprint.pprintln(
    "This is the list of words you can find in The Picture of Dorian Gray: " + dorianGrayWords
      .collect()
      .mkString(","))
}
