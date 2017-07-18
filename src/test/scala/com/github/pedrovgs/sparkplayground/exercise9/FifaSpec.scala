package com.github.pedrovgs.sparkplayground.exercise9

import com.github.pedrovgs.sparkplayground.exercise9.Fifa._
import com.holdenkarau.spark.testing.SharedSparkContext
import org.scalatest.{FlatSpec, Matchers}

class FifaSpec extends FlatSpec with Matchers with SharedSparkContext {

  "Fifa" should "find the fastest player" in {
    fastestPlayer shouldBe "Pierre-Emerick Aubameyang"
  }

  it should "calculate the average penalties per player" in {
    averagePenalties shouldBe 49.1657380 +- 0.1
  }

  it should "find the slowest player" in {
    slowestPlayer shouldBe "Horacio Ramírez"
  }

  it should "find the worst three players non being goalkeepers" in {
    worstThreePlayers shouldBe Array("Jérémie Bréchet", "Tommy Käßemodel", "Aaron Hughes")
  }

  it should "find the most rated club" in {
    mostRatedClub shouldBe "Real Madrid"
  }
}
