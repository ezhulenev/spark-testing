package com.github.ezhulenev.spark

import com.scalafi.openbook.{Side, OpenBookMsg}

object OrderFunctions extends Serializable {
  def isBuySide(order: OpenBookMsg) = order.side == Side.Buy

  def isSellSide(order: OpenBookMsg) = order.side == Side.Sell
}
