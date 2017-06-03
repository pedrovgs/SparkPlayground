package com.github.pedrovgs.sparkplayground.exercise3

import com.github.pedrovgs.sparkplayground.exercise3.Sets._
import com.holdenkarau.spark.testing.SharedSparkContext
import org.scalatest.{FlatSpec, Matchers}

class SetsSpec extends FlatSpec with Matchers with SharedSparkContext {

  "SetsSpec" should "find the first 5 words in Frankenstein" in {
    findFirstFiveFrankensteinWords() shouldBe List("clerval!",
                                                   "destination,",
                                                   "'felix,'",
                                                   "letter:",
                                                   "been")
  }

  it should "find the first 5 words in The Picture of Dorian Gray" in {
    findFirstFiveDorianGrayWords() shouldBe List("young",
                                                 "rises.",
                                                 "heroine.",
                                                 "harmonies",
                                                 "roses.")
  }

  it should "find the first 10 words found in both books when sorted by word length" in {
    findFirstTenWordsInBothBooks() shouldBe List(
      "http://www.gutenberg.org/1/7/174/",
      "http://gutenberg.net/license).",
      "http://www.gutenberg.org/8/84/",
      "http://gutenberg.net/license).",
      "http://pglaf.org/fundraising.",
      "http://pglaf.org/fundraising.",
      "industrious--painstaking,",
      "http://www.gutenberg.net",
      "http://www.gutenberg.net",
      "self-accusations.--poor"
    )
  }

  it should "find the first 5 words in the intersection of both books" in {
    findFirstFiveWordsInBothBooks() shouldBe List("been",
                                                  "breath",
                                                  "older.",
                                                  "knows",
                                                  "performed,")
  }

  it should "find the first 5 words in Frankenstein not bein part of Dorian Gray" in {
    findFirstFiveWordsInFrankensteinAndNotInDorian() shouldBe List("despairing,",
                                                                   "withdrawn",
                                                                   "abrupt",
                                                                   "sailors;",
                                                                   "morning's")
  }

  it should "know that The Picture of Dorian Gray contains more words than Frankenstein" in {
    getDorianGrayDifferentWordsCount() > getFrankensteinDifferentWordsCount() shouldBe true
  }

  it should "find the first 5 combinations of Frakenstein and Dorian books words" in {
    firstFiveCombinationsOfFrankensteinAndDorian() shouldBe List(("clerval!", "young"),
                                                                 ("clerval!", "rises."),
                                                                 ("clerval!", "heroine."),
                                                                 ("clerval!", "harmonies"),
                                                                 ("clerval!", "roses."))
  }

  it should "find the most repeated book in both books" in {
    mostRepeatedWord() shouldBe "better."
  }
}
