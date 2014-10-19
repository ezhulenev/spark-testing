package com.github.ezhulenev.spark

import com.scalafi.openbook.OpenBookMsg
import org.scalatest.FlatSpec

import scala.io.Codec


class OrdersFunctionsSpec extends FlatSpec with ConfiguredSparkSpec {

  def orders = {
    implicit val codec = Codec.ISO8859

    val is = this.getClass.getResourceAsStream("/openbookultraAA_N20130403_1_of_1")
    new OrdersFunctions(sc, OpenBookMsg.iterate(is))
  }

  "OrdersFunctions" should "correctly count Buy orders" in {
    val buyOrders = orders.countBuyOrders()
    assert(buyOrders("APC") == 39)
   }

  it should "correctly count Sell orders" in {
    val sellOrders = orders.countSellOrders()
    assert(sellOrders("ABX") == 31)
  }

}
