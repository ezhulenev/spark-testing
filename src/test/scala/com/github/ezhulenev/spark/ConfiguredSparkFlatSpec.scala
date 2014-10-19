package com.github.ezhulenev.spark

import com.typesafe.config.ConfigFactory
import org.apache.spark.{SparkContext, SparkConf}
import org.scalatest.{BeforeAndAfterAll, FlatSpec}
import org.slf4j.LoggerFactory


trait ConfiguredSparkFlatSpec extends FlatSpec with BeforeAndAfterAll {
  private val log = LoggerFactory.getLogger(classOf[ConfiguredSparkFlatSpec])

  private val config = ConfigFactory.load()

  private lazy val sparkConf = {
    val master = config.getString("spark.master")

    log.info(s"Create spark context. Master: $master")
    val assembledTests = sys.props.get("ASSEMBLED_TESTS")

    val baseConf = new SparkConf().
      setMaster(master).
      setAppName("SparkTestingExample")

    assembledTests match {
      case None =>
        log.warn(s"Assembled tests jar not found. Standalone Spark mode is not supported")
        baseConf
      case Some(path) =>
        log.info(s"Add assembled tests to Spark Context from: $path")
        baseConf.setJars(path :: Nil)
    }
  }

  lazy val sc = new SparkContext(sparkConf)

  override protected def afterAll(): Unit = {
    super.afterAll()
    sc.stop()
  }
}
