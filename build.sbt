name := "SparkPlayground"
version := "1.0"
scalaVersion := "2.11.11"

enablePlugins(ScalafmtPlugin)
CommandAliases.addCommandAliases()
addCompilerPlugin("org.scalamacros" % "paradise" % Versions.paradise cross CrossVersion.full)

libraryDependencies ++=  Seq(
  "org.apache.spark" %% "spark-core" % Versions.spark,
  "org.apache.spark" %% "spark-streaming" % Versions.spark,
  "org.apache.spark" %% "spark-sql" % Versions.spark,
  "org.apache.spark" %% "spark-mllib" % Versions.spark,
  "com.lihaoyi" %% "pprint" % Versions.pprint,
  "com.softwaremill.macmemo" %% "macros" % Versions.macmemo

)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % Versions.scalaTest % Test,
  "org.scalamock" %% "scalamock-scalatest-support" % Versions.scalaMock % Test,
  "org.scalacheck" %% "scalacheck" % Versions.scalaCheck % Test,
  "com.holdenkarau" %% "spark-testing-base" % Versions.sparkTestingBase % Test
)

fork in Test := true
parallelExecution in Test := false
javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled")