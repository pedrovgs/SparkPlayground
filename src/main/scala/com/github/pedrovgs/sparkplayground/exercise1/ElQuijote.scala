package com.github.pedrovgs.sparkplayground.exercise1

import com.github.pedrovgs.sparkplayground.{Resources, SparkApp}
import org.apache.spark.rdd.RDD

object ElQuijote extends App with SparkApp with Resources {

  private lazy val elQuijoteLines: RDD[String] = {
    val elQuijotePath = getFilePath("/exercise1/el-quijote.txt")
    sparkContext.textFile(elQuijotePath)
  }
  private lazy val elQuijoteWords: RDD[String] = elQuijoteLines.flatMap(line => line.split(" "))

  def firstSentence(): String = elQuijoteLines.first()

  def lastSentence(): String = elQuijoteLines.collect().last

  def numberOfLines(): Long = elQuijoteLines.count()

  def numberOfWords(): Long = elQuijoteWords.count()

  def numberOfSanchos(): Long = elQuijoteWords.filter(_.toLowerCase == "sancho").count()

  def numberOfChars(): Double = elQuijoteWords.map(_.length).sum()

  pprint.pprintln("The first sentence in the book is: " + firstSentence())
  pprint.pprintln("The last sentence in the book is: " + lastSentence())
  pprint.pprintln("The number of lines the book contains is: " + numberOfLines())
  pprint.pprintln("The number of words the book contains is: " + numberOfWords())
  pprint.pprintln("The number of times the word \"Sancho\" appears is: " + numberOfSanchos())
  pprint.pprintln("The number of chars the book contains is: " + numberOfChars())
}
