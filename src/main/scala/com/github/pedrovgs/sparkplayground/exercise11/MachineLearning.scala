package com.github.pedrovgs.sparkplayground.exercise11

import com.github.pedrovgs.sparkplayground.{Resources, SparkApp}
import org.apache.spark.ml.feature.Word2Vec
import org.apache.spark.mllib.classification.SVMWithSGD
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics
import org.apache.spark.mllib.linalg.{DenseVector, Vector}
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.rdd.RDD

object MachineLearning extends SparkApp with Resources {

  import sqlContext.implicits._

  // Read tweets data from a local csv file
  val tweetsData = sqlContext.read
    .option("header", "true")
    .option("inferSchema", "true")
    .csv(getFilePath("/exercise11/costaRicaEarthquakeTweets.csv"))
    .filter($"Informativeness".isNotNull)
    .rdd
    .map(row =>
      ({
        if (row.getAs[String]("Informativeness").equals("Related and informative")) {
          1.0
        } else {
          0.0
        }
      }, row.getAs[String]("TweetText").toLowerCase.split(" ")))
    .toDF("label", "tweet")
    .cache()

  val word2VectorModel = new Word2Vec()
    .setInputCol("tweet")
    .setOutputCol("features")
    .fit(tweetsData)

  // Calculate features
  val result = word2VectorModel
    .transform(tweetsData)

  // Transform as labeled points
  val convertedResult = MLUtils.convertVectorColumnsFromML(result, "features")
  val data: RDD[LabeledPoint] = convertedResult.rdd.map(row => {
    val label    = row.getAs[Double]("label")
    val features = row.getAs[DenseVector]("features")
    new LabeledPoint(label, features)
  })

  // Split data into training (60%) and test (40%).

  val splits: Array[RDD[LabeledPoint]] = data.randomSplit(Array(0.6, 0.4), seed = 11L)
  val training: RDD[LabeledPoint]      = splits(0).cache()
  val test: RDD[LabeledPoint]          = splits(1)

  // Run training algorithm to build the model
  val numIterations: Int = 100
  val svmModel           = SVMWithSGD.train(training, numIterations)

  // Clear the default threshold.
  svmModel.clearThreshold()

  // Compute raw scores on the test set.
  val scoreAndLabels = test.map { point =>
    val score = svmModel.predict(point.features)
    (score, point.label)
  }

  // Get evaluation metrics.
  val metrics = new BinaryClassificationMetrics(scoreAndLabels)
  val auPR    = metrics.areaUnderPR()
  val auROC   = metrics.areaUnderROC()

  pprint.pprintln("Time to save the models into disk and analize the area under PR and ROC:")
  val wordToVectorModelOutputFile = "./outputs/exercise11/word2VectorModel"
  delete(wordToVectorModelOutputFile)
  word2VectorModel.save(wordToVectorModelOutputFile)
  val svmModelOutputFile = "./outputs/exercise11/svmModel"
  delete(svmModelOutputFile)
  svmModel.save(sparkContext, svmModelOutputFile)

  pprint.pprintln("Area under PR = " + auPR)
  pprint.pprintln("Area under ROC = " + auROC)

  pprint.pprintln("Let's classify some tweets!")
  private val originalTweets: RDD[String] = sparkContext
    .parallelize(
      List(
        "Socorro, un terremoto en San Francisco. Necesitamos ayuda!",
        "Acabo de sentir un terremoto :S",
        "Miley Cyrus mola mil",
        "Vamos a la fiesta de cumpleaños de Pedro que llegamos tarde @davideme @delr3ves"
      ))
  val tweetsToAnalyze = originalTweets
    .map(_.split(" "))
    .toDF("tweet")
  val earthquakeTweetsResult = word2VectorModel.transform(tweetsToAnalyze)
  val convertedEarthquakeTweetsResult =
    MLUtils.convertVectorColumnsFromML(earthquakeTweetsResult, "features")
  val earthquakeTweetsData: RDD[Vector] =
    convertedEarthquakeTweetsResult.rdd.map(_.getAs[DenseVector]("features"))

  val prediction = svmModel.predict(earthquakeTweetsData)

  val predictionResult: RDD[(String, Double)] = originalTweets.zip(prediction)
  pprint.pprintln(
    "The following table shows the probability a tweet has to be a real earthquake informative tweet:")
  pprint.pprintln(
    predictionResult
      .map(tuple => "Tweet: " + tuple._1 + " - Probability: " + tuple._2)
      .collect())

}
