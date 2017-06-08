package com.github.pedrovgs.sparkplayground.exercise6

import com.github.pedrovgs.sparkplayground.exercise6.Movies._
import com.holdenkarau.spark.testing.SharedSparkContext
import org.scalatest.{FlatSpec, Matchers}

class MoviesSpec extends FlatSpec with Matchers with SharedSparkContext {
  "Movies" should "count the number of movies directed by James Camenron" in {
    numberOfMoviesDirectedByJamesCameron() shouldBe 7
  }

  it should "count the numer of movies directed by the top five directors" in {
    numberOfMoviesDirectedByTheTopFiveDirectors() shouldBe 9
  }
}
