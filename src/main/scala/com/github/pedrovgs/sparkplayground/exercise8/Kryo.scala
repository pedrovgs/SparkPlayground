package com.github.pedrovgs.sparkplayground.exercise8

import com.github.pedrovgs.sparkplayground.{Resources, SparkApp}
import org.apache.spark.rdd.RDD

object Kryo extends SparkApp with Resources {

  lazy val top10Pokemons: Array[Pokemon] = ???

  lazy val fastestPokemon: Pokemon = ???

}
