package com.github.pedrovgs.sparkplayground.exercise11

import com.github.pedrovgs.sparkplayground.SparkApp
import com.github.pedrovgs.sparkplayground.exercise5.ReadAndWrite.getFilePath
import org.apache.spark.ml.feature.Word2Vec
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.classification.SVMWithSGD
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics

object MachineLearning extends SparkApp {

  import sqlContext.implicits._

  // Read tweets data from a local csv file
  val tweetsData = sqlContext.read
    .option("header", "true")
    .option("inferSchema", "true")
    .csv(getFilePath("/exercise11/costaRicaEarthquakeTweets.csv"))
    .filter($"Informativeness".isNotNull)
    .rdd.map(row => ( {
    if (row.getAs[String]("Informativeness").equals("Not related")) {
      1.0
    } else {
      0.0
    }
  }, row.getAs[String]("TweetText").toLowerCase.split(" ")))
    .toDF("label", "tweet")
    .cache()

  // Calculate features
  val result = new Word2Vec()
    .setInputCol("tweet")
    .setOutputCol("features")
    .fit(tweetsData)
    .transform(tweetsData)

  // Transform as labeled points
  val data: RDD[LabeledPoint] = result.rdd.map(row => {
    val label = row.getAs[Double]("label")
    val features = Vectors.dense(row.getAs[Array[Double]]("features"))
    new LabeledPoint(label, features)
  })

  // Split data into training (60%) and test (40%).
  val splits: Array[RDD[LabeledPoint]] = data.randomSplit(Array(0.6, 0.4), seed = 11L)
  val training: RDD[LabeledPoint] = splits(0).cache()
  val test: RDD[LabeledPoint] = splits(1)

  // Run training algorithm to build the model
  val numIterations: Int = 100
  val model = SVMWithSGD.train(training, numIterations)

  // Clear the default threshold.
  model.clearThreshold()

  // Compute raw scores on the test set.
  val scoreAndLabels = test.map { point =>
    val score = model.predict(point.features)
    (score, point.label)
  }

  // Get evaluation metrics.
  val metrics = new BinaryClassificationMetrics(scoreAndLabels)
  val auPR = metrics.areaUnderPR()
  val auROC = metrics.areaUnderROC()

  pprint.pprintln("Area under PR = " + auPR)
  pprint.pprintln("Area under ROC = " + auROC)

}
