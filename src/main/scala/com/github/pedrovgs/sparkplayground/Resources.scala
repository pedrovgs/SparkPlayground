package com.github.pedrovgs.sparkplayground

trait Resources {
  def getFilePath(name: String): String = getClass.getResource(name).getPath

  def getOutputFilePath(name: String): String = "./outputs/" + name
}
