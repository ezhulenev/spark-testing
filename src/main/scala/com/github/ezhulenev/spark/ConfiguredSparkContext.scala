package com.github.ezhulenev.spark

import com.typesafe.config.ConfigFactory
import org.apache.spark.{SparkConf, SparkContext}


trait ConfiguredSparkContext {
  private[this] val config = ConfigFactory.load()

  private[this] val sparkConf = new SparkConf().
    setMaster(config.getString("spark.master")).
    setJars(SparkContext.jarOfClass(this.getClass).toSeq).
    setAppName("SparkTestingExample")

  lazy val sc = new SparkContext(sparkConf)
}
