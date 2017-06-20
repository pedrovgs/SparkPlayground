package com.github.pedrovgs.sparkplayground

import java.nio.file.{Files, Paths}

import org.apache.spark.SparkFiles

trait Resources {
  def getFilePath(name: String): String = {
    val jarPath = getClass.getResource(name).getPath
    val tmpPath = "file:///tmp/resources" + name
    if (exists(jarPath)) {
      jarPath
    } else if (exists(tmpPath)) {
      tmpPath
    } else {
      val fileName = name.substring(name.lastIndexOf("/") + 1)
      "file://" + SparkFiles.get(fileName)
    }
  }

  def getOutputFilePath(name: String): String = "./outputs/" + name

  private def exists(path: String): Boolean = Files.exists(Paths.get(path))
}
