package com.github.pedrovgs.sparkplayground

import java.nio.file.{Files, Paths}

trait Resources {
  def getFilePath(name: String): String = {
    val jarPath = getClass.getResource(name).getPath
    if (Files.exists(Paths.get(jarPath))) {
      jarPath
    } else {
      "file:///tmp/resources" + name
    }
  }

  def getOutputFilePath(name: String): String = "./outputs/" + name
}
