package com.github.pedrovgs.sparkplayground.exercise2

import com.github.pedrovgs.sparkplayground.exercise2.NumericalSeries.firstTenPrimeNumbers
import org.scalatest.{FlatSpec, Matchers}

class NumericalSeriesSpec extends FlatSpec with Matchers {

  "NumericalSeries" should "find the first 10 prime numbers" in {
    firstTenPrimeNumbers() shouldBe Array(2, 3, 5, 7, 11, 13, 17, 19, 23, 29)
  }

}
