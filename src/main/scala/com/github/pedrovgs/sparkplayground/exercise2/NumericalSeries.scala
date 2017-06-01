package com.github.pedrovgs.sparkplayground.exercise2

import com.github.pedrovgs.SparkApp
import org.apache.spark.rdd.RDD

import scala.annotation.tailrec
import scala.util.Random

object NumericalSeries extends App with SparkApp {

  private lazy val numbers: RDD[Int] = sparkContext.parallelize(0 until Int.MaxValue)
  private lazy val randomNumbers: RDD[Int] =
    sparkContext.parallelize(0 to 1000).map(_ => Random.nextInt()).persist()
  private lazy val fibonacciNumbers: RDD[Long] = fibonacciRDDs(50L)

  private def fibonacciRDDs(n: Long): RDD[Long] =
    sparkContext.parallelize(0L to n).map(fibonacci(_)).persist()

  def firstTenPrimeNumbers(): Array[Int] = numbers.filter(isPrime).take(10)

  def tenBiggestRandomNumbers(): (RDD[Int], Array[Int]) = (randomNumbers, randomNumbers.top(10))

  def tenSmallestRandomNumbers(): (RDD[Int], Array[Int]) =
    (randomNumbers, randomNumbers.takeOrdered(10))

  def fibonacciAndEvenValues(): Array[Long] = fibonacciNumbers.filter(isEven).collect()

  def fibonacciAndOddValues(): Array[Long] = fibonacciNumbers.filter(isOdd).collect()

  def sum60FibonacciValues(): Double = fibonacciRDDs(60L).sum()

  def sum6000FibonacciValuesUsingReduce(): Double = fibonacciRDDs(6000L).reduce((x, y) => x + y)
  def sum6000FibonacciValuesUsingFold(): Double   = fibonacciRDDs(6000L).fold(0)((x, y) => x + y)

  private def isPrime(n: Int): Boolean = {
    if (n <= 1) {
      false
    } else if (n == 2) {
      true
    } else {
      !(2 until n).exists(x => n % x == 0)
    }
  }

  private def fibonacci(n: Long): Long = {
    @tailrec
    def go(count: Long, prev: Long, acc: Long): Long = {
      if (count == 0) acc else go(count - 1, acc, acc + prev)
    }

    go(n, 1, 0)
  }

  private def isEven(n: Long): Boolean = n % 2 != 0

  private def isOdd(n: Long): Boolean = !isEven(n)

  pprint.pprintln("This is the list of the first 10 prime numbers: " + firstTenPrimeNumbers())
  pprint.pprintln(
    "This is the list of the 10 biggest numbers in a random numerical series: "
      + tenBiggestRandomNumbers()._2.mkString(","))
  pprint.pprintln(
    "This is the list of the 10 smallest numbers in a random numerical series: "
      + tenSmallestRandomNumbers()._2.mkString(","))
  pprint.pprintln(
    "This is the list of the values being even and part of the fibonacci series: "
      + fibonacciAndEvenValues().mkString(","))
  pprint.pprintln(
    "This is the list of the values being odd and part of the fibonacci series: "
      + fibonacciAndOddValues().mkString(","))
  pprint.pprintln(
    "The sum of the first 60 items in the fibonacci series is: " + sum60FibonacciValues())
  pprint.pprintln("This is a list of 1000 random integers: ")
  randomNumbers.foreach(pprint.pprintln(_))
  pprint.pprintln(
    "This is the sum of the first 6000 fibonacci numbers calculated using reduce action: "
      + sum6000FibonacciValuesUsingReduce())
  pprint.pprintln(
    "This is the sum of the first 6000 fibonacci numbers calculated using fold action: "
      + sum6000FibonacciValuesUsingFold())

}
