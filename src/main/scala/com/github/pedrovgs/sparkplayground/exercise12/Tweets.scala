package com.github.pedrovgs.sparkplayground.exercise12

import com.github.pedrovgs.sparkplayground.{Resources, SparkApp}

object Tweets extends SparkApp with Resources {

  lazy val mostTweetedAuthor: String = ???

  lazy val positiveTweetsCount: Long = ???

  lazy val positiveWordsCount: Long = ???

  lazy val negativeWordsCount: Long = ???

  lazy val positiveWordsCount2: Long = ???

  lazy val positiveWordsCountWithCogroup: Long = ???

  lazy val negativeWordsCount2: Long = ???

  lazy val positiveWordsCountedByKey: Long = ???

}
