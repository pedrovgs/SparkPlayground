package com.github.pedrovgs.sparkplayground.exercise3

import com.github.pedrovgs.sparkplayground.exercise3.Sets._
import com.holdenkarau.spark.testing.SharedSparkContext
import org.scalatest.{FlatSpec, Matchers}

class SetsSpec extends FlatSpec with Matchers with SharedSparkContext {

  "SetsSpec" should "find the first 5 words in Frankenstein" in {
    findFirstFiveFrankensteinWords() shouldBe List("Project",
                                                   "Gutenberg's",
                                                   "Frankenstein,",
                                                   "by",
                                                   "Mary")
  }

  it should "find the first 5 words in The Picture of Dorian Gray" in {
    findFirstFiveDorianGrayWords() shouldBe List("The", "Project", "Gutenberg", "EBook", "of")
  }
}
