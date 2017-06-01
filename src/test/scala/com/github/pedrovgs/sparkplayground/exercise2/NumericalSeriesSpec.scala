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

  it should "find every fibonacci number which is also even" in {
    fibonacciAndEvenValues() shouldBe Array(1, 1, 3, 5, 13, 21, 55, 89, 233, 377, 987, 1597, 4181,
      6765, 17711, 28657, 75025, 121393, 317811, 514229, 1346269, 2178309, 5702887, 9227465,
      24157817, 39088169, 102334155, 165580141, 433494437, 701408733, 1836311903, 2971215073L,
      7778742049L, 12586269025L)
  }

  it should "find every fibonacci number which is also odd" in {
    fibonacciAndOddValues() shouldBe Array(0, 2, 8, 34, 144, 610, 2584, 10946, 46368, 196418,
      832040, 3524578, 14930352, 63245986, 267914296L, 1134903170L, 4807526976L)
  }

}
