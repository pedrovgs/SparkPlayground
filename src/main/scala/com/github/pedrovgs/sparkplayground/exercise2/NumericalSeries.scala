package com.github.pedrovgs.sparkplayground.exercise2

import com.github.pedrovgs.SparkApp
import org.apache.spark.rdd.RDD

import scala.util.Random

object NumericalSeries extends App with SparkApp {

  private lazy val numbers: RDD[Int] = sparkContext.parallelize(0 until Int.MaxValue)
  private lazy val randomNumbers: RDD[Int] =
    sparkContext.parallelize(0 to 1000).map(_ => Random.nextInt()).persist()

  def firstTenPrimeNumbers(): Array[Int] = numbers.filter(isPrime).take(10)

  def tenBiggestRandomNumbers(): (RDD[Int], Array[Int]) = (randomNumbers, randomNumbers.top(10))

  def tenSmallestRandomNumbers(): (RDD[Int], Array[Int]) =
    (randomNumbers, randomNumbers.takeOrdered(10))

  private def isPrime(n: Int): Boolean = {
    if (n <= 1) {
      false
    } else if (n == 2) {
      true
    } else {
      !(2 until n).exists(x => n % x == 0)
    }
  }

  pprint.pprintln("This is the list of the first 10 prime numbers: " + firstTenPrimeNumbers())
  pprint.pprintln(
    "This is the list of the 10 biggest numbers in a random numerical series: "
      + tenBiggestRandomNumbers()._2.mkString(","))

}
