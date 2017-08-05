package com.github.pedrovgs.sparkplayground.exercise12

import com.github.pedrovgs.sparkplayground.exercise12.Tweets._
import com.holdenkarau.spark.testing.SharedSparkContext
import org.scalatest.{FlatSpec, Matchers}

class TweetsSpec extends FlatSpec with Matchers with SharedSparkContext {

  "Tweets" should "should find the author with more tweets associated" in {
    mostTweetedAuthor shouldBe "Drumgirl"
  }

  it should "find the number of positive tweets" in {
    positiveTweetsCount shouldBe 199999
  }

  it should "find the number of words associated to positive tweets" in {
    positiveWordsCount shouldBe 12093023
  }

  it should "find the number of words associated to negative tweets" in {
    negativeWordsCount shouldBe 24779363
  }
}
