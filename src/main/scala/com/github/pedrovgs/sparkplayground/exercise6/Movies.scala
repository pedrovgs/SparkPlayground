package com.github.pedrovgs.sparkplayground.exercise6

import com.github.pedrovgs.sparkplayground.SparkApp
import com.github.pedrovgs.sparkplayground.exercise5.ReadAndWrite.getFilePath
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.Dataset

object Movies extends App with SparkApp {

  def numberOfMoviesDirectedByJamesCameron(): Long = ???

  def numberOfMoviesDirectedByTheTopFiveDirectors(): Long = ???

  def numberOfMovies(): Long = ???

  def meanOfFacebookLikes(): Double = ???

  def totalDuration(): Double = ???

  def maxNumberOfLikes(): Integer = ???

  def minNumberOfLikes(): Integer = ???

  def moviesDurationVariance(): Double = ???

  def moviesStandardDeviation(): Double = ???

}
