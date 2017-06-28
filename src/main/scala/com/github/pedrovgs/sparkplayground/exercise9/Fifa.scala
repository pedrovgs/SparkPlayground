package com.github.pedrovgs.sparkplayground.exercise9

import com.github.pedrovgs.sparkplayground.{Resources, SparkApp}

object Fifa extends SparkApp with Resources {

  private def createTempView() = sqlContext.read
    .option("header", "true")
    .option("inferSchema", "true")
    .csv(getFilePath("/exercise9/fifa.csv"))
    .createOrReplaceTempView("fifa_players")

  lazy val fastestPlayer = {
    createTempView()
    sqlContext.sql("SELECT Name FROM fifa_players WHERE Speed = (SELECT Max(age) as Max_Speed FROM fifa_players) LIMIT 1").first().getAs[String]("Name")
  }

  pprint.pprintln("This is the fastest player: " + fastestPlayer)
}
