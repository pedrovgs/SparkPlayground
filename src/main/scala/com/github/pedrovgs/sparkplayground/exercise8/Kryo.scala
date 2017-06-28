package com.github.pedrovgs.sparkplayground.exercise8

import com.github.pedrovgs.sparkplayground.{Resources, SparkApp}
import org.apache.spark.rdd.RDD

object Kryo extends SparkApp with Resources {

  private lazy val pokemons: RDD[Pokemon] = {
    val path = getFilePath("/exercise8/pokemon.csv")
    sparkContext
      .textFile(path)
      .map(_.split(","))
      .map(attributes =>
        Pokemon(
          attributes(0),
          attributes(1),
          attributes(2),
          Option(attributes(3)),
          attributes(4).toInt,
          attributes(5).toInt,
          attributes(6).toInt,
          attributes(7).toInt,
          attributes(8).toInt,
          attributes(9).toInt,
          attributes(10).toInt,
          attributes(11).toInt,
          attributes(12).toBoolean
      ))
      .cache()
  }

  lazy val top10Pokemons: Array[Pokemon] = pokemons.top(10)

  lazy val fastestPokemon: Pokemon = top10Pokemons.head

  pprint.pprintln("This is the fastest of the strongest pokemons: " + fastestPokemon)
  pprint.pprintln("This is the list of the top 10 pokemons: ")
  pprint.pprintln(top10Pokemons)

}
