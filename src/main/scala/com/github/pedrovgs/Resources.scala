package com.github.pedrovgs

trait Resources {
  def getFilePath(name: String): String = getClass.getResource(name).getPath
}
