package com.github.pedrovgs.sparkplayground.exercise9

import com.github.pedrovgs.sparkplayground.exercise9.Fifa.fastestPlayer
import com.holdenkarau.spark.testing.SharedSparkContext
import org.scalatest.{FlatSpec, Matchers}

class FifaSpec extends FlatSpec with Matchers with SharedSparkContext {

  "Fifa" should "find the fastest player" in {
    fastestPlayer shouldBe "Ralf Fährmann"
  }
}
