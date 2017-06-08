package com.github.pedrovgs.sparkplayground.exercise6

import com.github.pedrovgs.SparkApp
import com.github.pedrovgs.sparkplayground.exercise5.ReadAndWrite.getFilePath
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.Dataset

object Movies extends App with SparkApp {

  private lazy val movies: Dataset[Movie] = {
    import sqlContext.implicits._
    sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .option("inferSchema", "true")
      .load(getFilePath("/exercise6/movies.csv"))
      .map(row => {
        val name          = row.getAs[String]("movie_title")
        val directorName  = row.getAs[String]("director_name")
        val duration      = row.getAs[Integer]("duration")
        val facebookLikes = row.getAs[Integer]("cast_total_facebook_likes")
        Movie(name, directorName, duration, facebookLikes)
      })
      .cache()
  }

  private lazy val topFiveDirectors: Array[String] = {
    movies.rdd
      .takeOrdered(5)(new Ordering[Movie] {
        override def compare(x: Movie, y: Movie): Int = x.facebookLikes - y.facebookLikes
      })
      .map(_.directorName)
  }

  private lazy val facebookLikes: RDD[Integer] = {
    import sqlContext.implicits._
    movies.map(_.facebookLikes).rdd
  }

  def numberOfMoviesDirectedByJamesCameron(): Long = {
    val counter = sparkContext.longAccumulator("JamesCameronMovies")
    movies.foreach { movie =>
      if (movie.directorName == "James Cameron") {
        counter.add(1)
      }
    }
    counter.value
  }

  def numberOfMoviesDirectedByTheTopFiveDirectors(): Long = {
    val broadcastDirectors = sparkContext.broadcast[Array[String]](topFiveDirectors)
    movies.filter(movie => broadcastDirectors.value.contains(movie.directorName)).count()
  }

  def numberOfMovies(): Long = {
    movies.count()
  }

  def meanOfFacebookLikes(): Double = {
    import sqlContext.implicits._
    movies.map(_.facebookLikes.toDouble).rdd.mean()
  }

  def totalDuration(): Double = {
    import sqlContext.implicits._
    movies.flatMap(movie => Option(movie.duration).map(_.toDouble)).rdd.sum()
  }

  def maxNumberOfLikes(): Integer = {
    facebookLikes.max()
  }

  def minNumberOfLikes(): Integer = {
    facebookLikes.min()
  }

}
