package com.github.pedrovgs.sparkplayground.exercise9

import com.github.pedrovgs.sparkplayground.{Resources, SparkApp}

object Fifa extends SparkApp with Resources {

  import sqlContext.implicits._

  private lazy val fifaPlayers = sqlContext.read
    .option("header", "true")
    .option("inferSchema", "true")
    .csv(getFilePath("/exercise9/fifa.csv"))

  private def createTempView() = fifaPlayers.createOrReplaceTempView("fifa_players")

  lazy val fastestPlayer: String = {
    createTempView()
    sqlContext
      .sql(
        "SELECT Name FROM fifa_players WHERE Speed = (SELECT Max(age) as Max_Speed FROM fifa_players) LIMIT 1")
      .first()
      .getAs[String]("Name")
  }

  lazy val averagePenalties: Double = {
    createTempView()
    sqlContext.sql("SELECT AVG(Penalties) FROM fifa_players").first().getDouble(0)
  }

  lazy val slowestPlayer: String =
    fifaPlayers.select("Name", "Speed").sort("Speed").first().getString(0)

  lazy val worstThreePlayers: Array[String] = {
    fifaPlayers
      .sort("Speed", "Strength", "Stamina", "Balance", "Agility", "Shot_Power")
      .limit(5)
      .filter(!_.getAs[String]("Preffered_Position").equalsIgnoreCase("GK"))
      .map(_.getAs[String]("Name"))
      .collect()
  }

  lazy val mostRatedClub: String = fifaPlayers
    .groupBy("Club")
    .sum("Rating")
    .filter(row => {
      val club = row.getAs[String]("Club")
      !club.equalsIgnoreCase("Free Agent") && !club.equalsIgnoreCase("Free Agents")
    })
    .sort($"sum(Rating)".desc)
    .first()
    .getAs[String]("Club")

  pprint.pprintln("This is the fifa players csv schema:")
  fifaPlayers.printSchema()
  pprint.pprintln("These are some players:")
  fifaPlayers.show()
  pprint.pprintln("This is the fastest player: " + fastestPlayer)
  pprint.pprintln("This is the average number of penalties per player: " + averagePenalties)
  pprint.pprintln("This is the slowest player: " + slowestPlayer)
  pprint.pprintln("This is the best three players we could find: " + worstThreePlayers)
  pprint.pprintln("This is the most rated club: " + mostRatedClub)
}
