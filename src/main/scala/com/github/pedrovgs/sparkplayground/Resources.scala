package com.github.pedrovgs.sparkplayground

import java.nio.file.{Files, Paths}

import org.apache.spark.SparkFiles

trait Resources {
  def getFilePath(name: String): String = {
    val jarPath    = getClass.getResource(name).getPath
    val volumePath = "/tmp/data/resources" + name
    if (exists(jarPath)) {
      pprint.pprintln("Reading existing jar path at: " + jarPath)
      jarPath
    } else if (exists(volumePath)) {
      pprint.pprintln("Reading existing volume path at: " + volumePath)
      "file://" + volumePath + "/"
    } else {
      val fileName   = name.substring(name.lastIndexOf("/") + 1)
      val workerPath = SparkFiles.get(fileName)
      if (exists(workerPath)) {
        pprint.pprintln("Reading existing worker path at: " + workerPath)
      } else {
        pprint.pprintln("Couln't find file: " + fileName)
      }
      "file://" + workerPath + "/"
    }
  }

  def getOutputFilePath(name: String): String = "./outputs/" + name

  private def exists(path: String): Boolean = {
    pprint.pprintln("Checking the existence of a file at path: " + path)
    Files.exists(Paths.get(path))
  }
}
