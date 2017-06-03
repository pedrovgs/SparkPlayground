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

  it should "find the first 10 words found in both books when sorted by word length" in {
    findFirstTenWordsInBothBooks() shouldBe List(
      "http://www.gutenberg.org/1/7/174/",
      "http://www.gutenberg.org/8/84/",
      "http://gutenberg.net/license).",
      "http://gutenberg.net/license).",
      "http://pglaf.org/fundraising.",
      "http://pglaf.org/fundraising.",
      "industrious--painstaking,",
      "http://www.gutenberg.net",
      "http://www.gutenberg.net",
      "self-accusations.--Poor"
    )
  }
}
