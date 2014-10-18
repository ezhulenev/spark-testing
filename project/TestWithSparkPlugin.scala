import sbt._
import sbt.Keys._
import sbt.Def.Initialize
import sbtassembly.Plugin.AssemblyKeys
import sbtsequential.Plugin._

object TestWithSparkPlugin extends sbt.Plugin {

  import TestWithSparkKeys._
  import AssemblyKeys._

  object TestWithSparkKeys {
    lazy val testAssembled        = TaskKey[Unit]("test-assembled", "Run tests with standalone Spark cluster")
    lazy val assembledTestsProp   = SettingKey[String]("assembled-tests-prop", "Environment variable name used to pass assembled jar name to test")
  }

  lazy val baseTestWithSparkSettings: Seq[sbt.Def.Setting[_]] = Seq(
    testAssembled        := TestWithSpark.testWithSparkTask.value,
    assembledTestsProp   := "ASSEMBLED_TESTS"
  )

  lazy val testWithSparkSettings: Seq[sbt.Def.Setting[_]] = baseTestWithSparkSettings

  object TestWithSpark {

    def assemblyTestsJarTask: Initialize[Task[File]] = Def.task {
      val assembled = (assembly in Test).value
      sys.props(assembledTestsProp.value) = assembled.getAbsolutePath
      assembled
    }

    private def runTests = Def.task {
      (test in Test).value
    }

    def testWithSparkTask: Initialize[Task[Unit]] = Def.sequentialTask {
      assemblyTestsJarTask.value
      runTests.value
    }
  }
}