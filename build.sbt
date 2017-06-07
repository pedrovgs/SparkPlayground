name := "SparkPlayground"
version := "1.0"
scalaVersion := "2.11.11"

enablePlugins(ScalafmtPlugin)
CommandAliases.addCommandAliases()

libraryDependencies ++=  Seq(
  "org.apache.spark" %% "spark-core" % Versions.spark,
  "org.apache.spark" %% "spark-streaming" % Versions.spark,
  "org.apache.spark" %% "spark-sql" % Versions.spark,
  "org.apache.spark" %% "spark-mllib" % Versions.spark,
  "com.lihaoyi" %% "pprint" % Versions.pprint,
  "com.databricks" %% "spark-csv" % Versions.sparkCsv,
  "com.trueaccord.scalapb" %% "sparksql-scalapb" % Versions.sparkScalaPb
)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % Versions.scalaTest % Test,
  "org.scalamock" %% "scalamock-scalatest-support" % Versions.scalaMock % Test,
  "org.scalacheck" %% "scalacheck" % Versions.scalaCheck % Test,
  "com.holdenkarau" %% "spark-testing-base" % Versions.sparkTestingBase % Test
)

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)
libraryDependencies += "com.trueaccord.scalapb" %% "scalapb-runtime" % com.trueaccord.scalapb.compiler.Version.scalapbVersion % "protobuf"
assemblyShadeRules in assembly := Seq(
  ShadeRule.rename("com.google.protobuf.**" -> "shadeproto.@1").inAll
)


fork in Test := true
parallelExecution in Test := false
javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled")