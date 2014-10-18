package com.github.ezhulenev.spark

import com.typesafe.config.ConfigFactory
import org.apache.spark.{SparkConf, SparkContext}
import org.slf4j.LoggerFactory


trait ConfiguredSparkContext {
  private val log = LoggerFactory.getLogger(classOf[ConfiguredSparkContext])

  private val config = ConfigFactory.load()

  private lazy val sparkConf = {
    val master = config.getString("spark.master")
    log.info(s"Create spark context. Master: $master}")
    new SparkConf().
      setMaster(master).
      setJars(SparkContext.jarOfClass(this.getClass).toSeq).
      setAppName("SparkTestingExample")
  }

  lazy val sc = new SparkContext(sparkConf)
}
