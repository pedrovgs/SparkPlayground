package com.github.pedrovgs.sparkplayground.exercise12

import com.github.pedrovgs.sparkplayground.{Resources, SparkApp}
import org.apache.spark.rdd.RDD

object Tweets extends SparkApp with Resources {

  private lazy val plainTweets: RDD[Array[String]] = readTweets("/exercise12/tweets.csv")

  private lazy val plainExtraTweets: RDD[Array[String]] = readTweets("/exercise12/tweets2.csv")

  private lazy val tweetsBySentiments: RDD[(String, String)] = plainTweets
    .map { values =>
      val sentiment = values(0)
      val content   = values(5)
      (sentiment, content)
    }
    .cache()

  private lazy val extraTweetsBySentiments: RDD[(String, String)] = plainExtraTweets
    .map { values =>
      val sentiment = values(0)
      val content   = values(5)
      (sentiment, content)
    }
    .cache()

  private lazy val everyTweetBySentiments: RDD[(String, String)] = tweetsBySentiments
    .join(extraTweetsBySentiments)
    .mapValues {
      case (t1, t2) => t1 + t2
    }
    .cache()

  lazy val mostTweetedAuthor: String = plainTweets
    .map { values: Array[String] =>
      val author = values(4)
      val tweets = values(5)
      (author, tweets)
    }
    .groupByKey
    .mapValues { tweets =>
      tweets.size
    }
    .sortBy(_._2)
    .first()
    ._1

  lazy val positiveTweetsCount: Long = plainTweets
    .filter { values =>
      values(0).toInt == 4
    }
    .count()

  lazy val positiveWordsCount: Long = countSentimentWords(tweetsBySentiments, "4")

  lazy val negativeWordsCount: Long = countSentimentWords(tweetsBySentiments, "0")

  lazy val positiveWordsCount2: Long = countSentimentWords(everyTweetBySentiments, "4")

  lazy val negativeWordsCount2: Long = countSentimentWords(everyTweetBySentiments, "0")

  pprint.pprintln("The author with more tweets is:" + mostTweetedAuthor)
  pprint.pprintln("The number of positive tweets is: " + positiveTweetsCount)
  pprint.pprintln("The number of words associated to positive tweets is: " + positiveWordsCount)
  pprint.pprintln("The number of words associated to negative tweets is: " + negativeWordsCount)
  pprint.pprintln(
    "The number of words associated to positive tweets plus extra tweets is: " + positiveWordsCount2)
  pprint.pprintln(
    "The number of words associated to negative tweets plus extra tweets is: " + negativeWordsCount2)

  private def readTweets(path: String): RDD[Array[String]] =
    sparkContext
      .textFile(getFilePath(path))
      .map(_.replace("\"", ""))
      .map(_.split(","))
      .cache()

  private def countSentimentWords(tweets: RDD[(String, String)], sentiment: String) = {
    tweets
      .mapValues(_.length)
      .aggregateByKey(0)(_ + _, _ + _)
      .collectAsMap()(sentiment)
  }
}
