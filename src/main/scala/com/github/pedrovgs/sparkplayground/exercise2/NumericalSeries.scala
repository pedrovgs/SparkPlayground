package com.github.pedrovgs.sparkplayground.exercise2

import com.github.pedrovgs.SparkApp
import org.apache.spark.rdd.RDD

object NumericalSeries extends App with SparkApp {

  private lazy val numbers: RDD[Int] = sparkContext.parallelize(Range(0, Int.MaxValue))

  def firstTenPrimeNumbers(): Array[Int] = numbers.filter(isPrime).take(10)

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

}
