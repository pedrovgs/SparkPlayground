package com.github.pedrovgs.sparkplayground.exercise2

import com.github.pedrovgs.sparkplayground.SparkApp
import org.apache.spark.rdd.RDD

import scala.annotation.tailrec
import scala.util.Random

object NumericalSeries extends SparkApp {

  def firstTenPrimeNumbers(): Array[Int] = ???

  def tenBiggestRandomNumbers(): (RDD[Int], Array[Int]) = ???

  def tenSmallestRandomNumbers(): (RDD[Int], Array[Int]) = ???

  def fibonacciAndEvenValues(): Array[Long] = ???

  def fibonacciAndOddValues(): Array[Long] = ???

  def sum60FibonacciValues(): Double = ???

  def sum6000FibonacciValuesUsingReduce(): Double = ???

  def sum6000FibonacciValuesUsingFold(): Double = ???

}
