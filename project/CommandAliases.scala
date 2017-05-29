import sbt.addCommandAlias

object CommandAliases {

  def addCommandAliases(): sbt.internals.DslEntry = {
    addCommandAlias("format", ";scalafmt;test:scalafmt")
  }

}