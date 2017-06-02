package com.github.pedrovgs.sparkplayground.exercise1

import com.github.pedrovgs.sparkplayground.exercise1.ElQuijote._
import com.holdenkarau.spark.testing.SharedSparkContext
import org.scalatest.{FlatSpec, Matchers}

class ElQuijoteSpec extends FlatSpec with Matchers with SharedSparkContext {

  "El Quijote" should "find the first sentence in the book" in {
    firstSentence shouldBe "BIBLIOTECA PERLA"
  }

  it should "find the last sentence in the book" in {
    lastSentence shouldBe "Don Quijote. .Al"
  }

  it should "count the number of lines in the book" in {
    numberOfLines shouldBe 46277
  }

  it should "count the number of words in the book" in {
    numberOfWords shouldBe 409855
  }

  it should "count the times the word \"Sancho\" appears in the book" in {
    numberOfSanchos shouldBe 761
  }

  it should "count the number of chars in the book" in {
    numberOfChars shouldBe 1754013
  }

}
