package com.github.pedrovgs.sparkplayground

import java.nio.file.{Files, Paths}

import org.apache.spark.SparkFiles

trait Resources {
  def getFilePath(name: String): String = {
    val jarPath = getClass.getResource(name).getPath
    if (Files.exists(Paths.get(jarPath))) {
      jarPath
    } else {
      val lastSeparator = name.lastIndexOf("/")
      SparkFiles.get(name.substring(lastSeparator))
    }
  }

  def getOutputFilePath(name: String): String = "./outputs/" + name
}
