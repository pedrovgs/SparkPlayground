package com.github.pedrovgs.sparkplayground.exercise2

import com.github.pedrovgs.sparkplayground.exercise2.NumericalSeries._
import com.holdenkarau.spark.testing.SharedSparkContext
import org.scalatest.{FlatSpec, Matchers}

class NumericalSeriesSpec extends FlatSpec with Matchers with SharedSparkContext {

  "NumericalSeries" should "find the first 10 prime numbers" in {
    firstTenPrimeNumbers() shouldBe Array(2, 3, 5, 7, 11, 13, 17, 19, 23, 29)
  }

  it should "find the ten biggest numbers in a random values set" in {
    val result            = tenBiggestRandomNumbers()
    val numbers           = result._1.collect()
    val tenBiggestNumbers = result._2.sortWith(_ > _)
    numbers.sortWith(_ > _).take(10) shouldBe tenBiggestNumbers
  }

  it should "find the ten smallest numbers in a random values set" in {
    val result             = tenSmallestRandomNumbers()
    val numbers            = result._1.collect()
    val tenSmallestNumbers = result._2.sortWith(_ < _)
    numbers.sortWith(_ < _).take(10) shouldBe tenSmallestNumbers
  }

}
