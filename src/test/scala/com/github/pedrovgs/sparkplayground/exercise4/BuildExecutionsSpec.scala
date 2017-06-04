package com.github.pedrovgs.sparkplayground.exercise4

import com.github.pedrovgs.sparkplayground.exercise4.BuildExecutions._
import com.holdenkarau.spark.testing.SharedSparkContext
import org.scalatest.{FlatSpec, Matchers}

class BuildExecutionsSpec extends FlatSpec with Matchers with SharedSparkContext {
  "BuildExecutions" should "find the first five execution times grouped by name" in {
    firstFiveExecutionTimesGroupedByName().toSeq.sortWith(_._2 > _._2) shouldBe List(
      ("crashlyticsUploadDistributionProductionRelease", 45307665737L),
      ("incrementalBetaDebugJavaCompilationSafeguard", 8770130557L),
      ("transformClassesWithJarMergingForBetaRelease", 4691684941L),
      ("generateBetaReleaseBuildConfig", 957192910L),
      ("assembleProductionDebugUnitTest", 9333096L)
    )
  }

  it should "transform milliseconds execution times to minutes" in {
    firstFiveTasksExecutionTimesInMinutes().toSeq.sortWith(_._2 > _._2) shouldBe List(
      ("crashlyticsUploadDistributionProductionRelease", 755127),
      ("incrementalBetaDebugJavaCompilationSafeguard", 146168),
      ("transformClassesWithJarMergingForBetaRelease", 78194),
      ("generateBetaReleaseBuildConfig", 15953),
      ("assembleProductionDebugUnitTest", 155)
    )
  }

  it should "find all the build task names" in {
    listOfTasks shouldBe List(
      "transformClassesWithJarMergingForBetaRelease",
      "assembleProductionDebugUnitTest",
      "crashlyticsUploadDistributionProductionRelease",
      "incrementalBetaDebugJavaCompilationSafeguard",
      "generateBetaReleaseBuildConfig"
    )
  }

  it should "find the slowest task" in {
    slowestTask() shouldBe ("connectedProductionDebugAndroidTest", 13446052122318L)
  }

  it should "find the fastest task" in {
    fastestTask() shouldBe ("validateSigningBetaDebugAndroidTest", 324119L)
  }

  it should "find the first five task names sorted by task name" in {
    findFirstFiveTasksNamesSortedByName() shouldBe List(
      "verifyMode",
      "validateSigningProductionRelease",
      "validateSigningProductionDebugAndroidTest",
      "validateSigningProductionDebug",
      "validateSigningBetaRelease"
    )
  }

  it should "find build tasks greater than sixty minutes" in {
    findExecutionTasksGreaterThanOneMinute() shouldBe Map(
      ("connectedProductionDebugAndroidTest" -> 13446052122318L))
  }

  it should "calculate total execution time" in {
    totalExecutionTime() shouldBe 4.2673302104925E13
  }
}
