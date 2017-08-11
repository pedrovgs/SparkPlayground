package com.github.pedrovgs.sparkplayground.exercise3

import com.github.pedrovgs.sparkplayground.{Resources, SparkApp}
import org.apache.spark.rdd.RDD

object Sets extends SparkApp with Resources {

  def findFirstFiveFrankensteinWords(): Array[String] = ???

  def findFirstFiveDorianGrayWords(): Array[String] = ???

  def findFirstTenWordsInBothBooks(): Array[String] = ???

  def findFirstFiveWordsInBothBooks(): Array[String] = ???

  def findFirstFiveWordsInFrankensteinAndNotInDorian(): Array[String] = ???

  def getFrankensteinDifferentWordsCount(): Long = ???

  def getDorianGrayDifferentWordsCount(): Long = ???

  def firstFiveCombinationsOfFrankensteinAndDorian(): Array[(String, String)] = ???

  def mostRepeatedWord(): String = ???

}
