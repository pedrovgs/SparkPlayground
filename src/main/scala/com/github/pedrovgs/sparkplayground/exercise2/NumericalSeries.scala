package com.github.pedrovgs.sparkplayground.exercise2

import com.github.pedrovgs.SparkApp
import com.softwaremill.macmemo.memoize
import org.apache.spark.rdd.RDD

import scala.annotation.tailrec
import scala.util.Random
import scala.concurrent.duration._

object NumericalSeries extends App with SparkApp {

  private lazy val numbers: RDD[Int] = sparkContext.parallelize(0 until Int.MaxValue)
  private lazy val randomNumbers: RDD[Int] =
    sparkContext.parallelize(0 to 1000).map(_ => Random.nextInt()).persist()
  private lazy val fibonacciNumbers: RDD[Long] =
    sparkContext.parallelize(0L to 50L).map(fibonacci(_)).persist()

  def firstTenPrimeNumbers(): Array[Int] = numbers.filter(isPrime).take(10)

  def tenBiggestRandomNumbers(): (RDD[Int], Array[Int]) = (randomNumbers, randomNumbers.top(10))

  def tenSmallestRandomNumbers(): (RDD[Int], Array[Int]) =
    (randomNumbers, randomNumbers.takeOrdered(10))

  def fibonacciAndEvenValues(): Array[Long] = fibonacciNumbers.filter(_ % 2 != 0).collect()

  @memoize(maxSize = 20000, expiresAfter = 2 hours)
  private def isPrime(n: Int): Boolean = {
    if (n <= 1) {
      false
    } else if (n == 2) {
      true
    } else {
      !(2 until n).exists(x => n % x == 0)
    }
  }

  @memoize(maxSize = 20000, expiresAfter = 2 hours)
  private def fibonacci(n: Long): Long = {
    @tailrec
    def go(count: Long, prev: Long, acc: Long): Long = {
      if (count == 0) acc else go(count - 1, acc, acc + prev)
    }
    go(n, 1, 0)
  }

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
}
