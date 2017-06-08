package com.github.pedrovgs.sparkplayground.exercise6

import com.github.pedrovgs.sparkplayground.exercise6.Movies._
import com.holdenkarau.spark.testing.SharedSparkContext
import org.scalatest.{FlatSpec, Matchers}

class MoviesSpec extends FlatSpec with Matchers with SharedSparkContext {
  "Movies" should "count the number of movies directed by James Camenron" in {
    numberOfMoviesDirectedByJamesCameron() shouldBe 7
  }

  it should "count the number of movies directed by the top five directors" in {
    numberOfMoviesDirectedByTheTopFiveDirectors() shouldBe 9
  }

  it should "count the number of movies" in {
    numberOfMovies() shouldBe 5043
  }

  it should "calculate the mean of facebook likes per movie" in {
    meanOfFacebookLikes() shouldBe 9699.063850882436 +- 0.1
  }

  it should "calculate the sum of movies duration" in {
    totalDuration() shouldBe 539007.0 +- 0.1
  }

  it should "find the max number of likes" in {
    maxNumberOfLikes() shouldBe 656730
  }

  it should "find the min number of likes" in {
    minNumberOfLikes() shouldBe 0
  }
}
