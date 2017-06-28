package com.github.pedrovgs.sparkplayground.exercise8

import com.holdenkarau.spark.testing.SharedSparkContext
import org.scalatest.{FlatSpec, Matchers}

class KryoSpec extends FlatSpec with Matchers with SharedSparkContext {

  "Kryo" should "find the fastest of the strongests pokemons" in {
    fastestPokemon.name shouldBe "MewtwoMega Mewtwo Y"
  }

  it should "find the top 10 pokemons" in {
    top10Pokemons.map(_.name).take(2) shouldBe Array("MewtwoMega Mewtwo Y", "MewtwoMega Mewtwo X")

  }

}
