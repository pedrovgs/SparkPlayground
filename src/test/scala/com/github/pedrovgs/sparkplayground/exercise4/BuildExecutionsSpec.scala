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
}
