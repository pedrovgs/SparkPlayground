package com.github.pedrovgs.sparkplayground.exercise5

import com.github.pedrovgs.sparkplayground.exercise5.ReadAndWrite._
import com.holdenkarau.spark.testing.SharedSparkContext
import org.scalatest.{FlatSpec, Matchers}

class ReadAndWriteSpec extends FlatSpec with Matchers with SharedSparkContext {

  "ReadAndWrite" should "read a JSON file, sort by last name and save it again" in {
    readAndWriteJson()
    sparkContext
      .textFile("./outputs/firstUser.json")
      .first() shouldBe "{\"name\":{\"title\":\"mr\",\"first\":\"emre\",\"last\":\"adal\"}}"
  }
}
