name := "SparkPlayground"
version := "1.0"
scalaVersion := "2.11.11"
val sparkVersion = "2.1.1"

enablePlugins(ScalafmtPlugin)

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-streaming" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-mllib" % sparkVersion,
  "com.lihaoyi" %% "pprint" % "0.5.0"
)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.3" % Test,
  "org.scalamock" %% "scalamock-scalatest-support" % "3.5.0" % Test,
  "org.scalacheck" %% "scalacheck" % "1.13.4" % Test
)
