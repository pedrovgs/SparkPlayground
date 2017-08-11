package com.github.pedrovgs.sparkplayground.exercise5

import com.github.pedrovgs.sparkplayground.{Resources, SparkApp}
import com.github.pedrovgs.sparkplayground.exercise5.model.ProtoUser
import com.trueaccord.scalapb.spark._
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.DataFrame

import scala.util.Try

object ReadAndWrite extends SparkApp with Resources {

  def readAndWriteText(): Unit = ???

  def readAndWriteJson(): Unit = ???

  def readAndWriteCSV(): Unit = ???

  def readAndWriteProtocolBuffer(): Unit = ???

  def readAndWriteObjectFile(): Unit = ???

  def readAndWriteGzipFile(): Unit = ???

}
