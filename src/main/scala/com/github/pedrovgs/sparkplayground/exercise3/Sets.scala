package com.github.pedrovgs.sparkplayground.exercise3

import com.github.pedrovgs.SparkApp
import org.apache.spark.rdd.RDD

object Sets extends App with SparkApp {

  private lazy val frankensteinWords: RDD[String] = extractDistinctWords(
    "/exercise3/frankenstein.txt")
  private lazy val dorianGrayWords: RDD[String] = extractDistinctWords(
    "/exercise3/the-picture-of-dorian-gray.txt")
  private lazy val bothBooksWordsSortedBySize: RDD[String] = frankensteinWords
    .union(dorianGrayWords)
    .sortBy(_.length, ascending = false)
    .persist()
  private lazy val intersectionOfBooks: RDD[String] =
    frankensteinWords.intersection(dorianGrayWords).persist()
  private lazy val frankensteinMinusDorian: RDD[String] =
    frankensteinWords.subtract(dorianGrayWords).persist()

  def findFirstFiveFrankensteinWords(): Array[String] = frankensteinWords.take(5)

  def findFirstFiveDorianGrayWords(): Array[String] = dorianGrayWords.take(5)

  def findFirstTenWordsInBothBooks(): Array[String] = bothBooksWordsSortedBySize.take(10)

  def findFirstFiveWordsInBothBooks(): Array[String] = intersectionOfBooks.take(5)

  def findFirstFiveWordsInFrankensteinAndNotInDorian(): Array[String] =
    frankensteinMinusDorian.take(5)

  def getFrankensteinDifferentWordsCount(): Long = frankensteinWords.count()

  def getDorianGrayDifferentWordsCount(): Long = dorianGrayWords.count()

  private def extractDistinctWords(fileName: String): RDD[String] = {
    val filePath = getClass.getResource(fileName).getPath
    sparkContext.textFile(filePath).flatMap(_.split(" ")).filter(_.nonEmpty).distinct().persist()
  }

  pprint.pprintln(
    "This is the list of words you can find in Frankenstein: " + frankensteinWords
      .collect()
      .mkString(","))
  pprint.pprintln(
    "This is the list of words you can find in The Picture of Dorian Gray: " + dorianGrayWords
      .collect()
      .mkString(","))
  pprint.pprintln(
    "This is the sum of words found in the two books sorted by word length: "
      + bothBooksWordsSortedBySize.collect().mkString(","))
  pprint.pprintln(
    "This is the intersection of words in this two books: " + intersectionOfBooks
      .collect()
      .mkString(","))
  pprint.pprintln(
    "This is the list of words bein part of Frankenstein but not part of THe Picture of Dorian Gray: "
      + frankensteinMinusDorian.collect().mkString(",")
  )
  pprint.pprintln(
    "The number of different words in Frankenstein is: " + getFrankensteinDifferentWordsCount)
  pprint.pprintln(
    "The number of different words in The Picture of Dorian Gray is: " + getDorianGrayDifferentWordsCount)

}
