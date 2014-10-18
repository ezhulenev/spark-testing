package com.github.ezhulenev.spark

import com.scalafi.openbook.OpenBookMsg
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._


class OrdersFunctions(@transient sc: SparkContext, orders: Iterator[OpenBookMsg]) extends Serializable {

  private val ordersRDD = sc.parallelize(orders.toSeq)

  def countBuyOrders(): Map[String, Long] =
    countOrders(OrderFunctions.isBuySide)

  def countSellOrders(): Map[String, Long] =
    countOrders(OrderFunctions.isSellSide)

  private def countOrders(filter: OpenBookMsg => Boolean): Map[String, Long] = {
    ordersRDD.filter(filter).
      map(order => (order.symbol, order)).
      countByKey().toMap
  }

}
