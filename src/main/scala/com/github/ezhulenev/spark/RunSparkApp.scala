package com.github.ezhulenev.spark

import com.scalafi.openbook.OpenBookMsg
import org.slf4j.LoggerFactory

import scala.io.Codec


object RunSparkApp extends App with ConfiguredSparkContext {
  private val log = LoggerFactory.getLogger("com.github.ezhulenev.SparkTestingExample")

  log.info(s"Count Orders by symbol")

  implicit val codec = Codec.ISO8859

  val is = this.getClass.getResourceAsStream("/openbookultraAA_N20130403_1_of_1")
  val orders = new OrdersFunctions(sc, OpenBookMsg.iterate(is))

  def printTopSymbols(counts: Map[String, Long]) =
    counts.toVector.sortBy(_._2).reverse.take(10) foreach {
      case (symbol, count) => log.info(s" - $symbol: $count")
    }

  log.info(s"Buy Orders:")
  printTopSymbols(orders.countBuyOrders())

  log.info(s"Sell Orders:")
  printTopSymbols(orders.countSellOrders())

  sc.stop()
}
