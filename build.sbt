name := "SparkPlayground"
version := "1.0"
scalaVersion := "2.11.11"

enablePlugins(ScalafmtPlugin)
CommandAliases.addCommandAliases()

libraryDependencies ++=  Seq(
  "org.apache.spark" %% "spark-core" % Versions.sparkVersion,
  "org.apache.spark" %% "spark-streaming" % Versions.sparkVersion,
  "org.apache.spark" %% "spark-sql" % Versions.sparkVersion,
  "org.apache.spark" %% "spark-mllib" % Versions.sparkVersion,
  "com.lihaoyi" %% "pprint" % Versions.pprint
)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % Versions.scalaTest % Test,
  "org.scalamock" %% "scalamock-scalatest-support" % Versions.scalaMock % Test,
  "org.scalacheck" %% "scalacheck" % Versions.scalaCheck % Test
)