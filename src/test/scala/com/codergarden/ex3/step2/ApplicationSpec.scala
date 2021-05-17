package com.codergarden.ex3.step2

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class ApplicationSpec extends AnyFlatSpec  with ScalaCheckPropertyChecks {

  "The Checkout (offers applied) with a shoppingCard containing an empty list" should "get 0" in {
    val shoppingCard = Shop.ShoppingCart(Nil)
    Shop.Checkout(shoppingCard).cost shouldEqual 0D
  }

  "The Checkout (offers applied) with a shoppingCard containing 2 Apples and 3 Oranges" should "get the price of 1 Apple and 2 Oranges" in {
    val shoppingCard = Shop.ShoppingCart(List("Apple","Apple","Orange","Orange","Orange"))
    val expectedCostWithDiscount = (1 * Shop.Apple.cost) + (2 * Shop.Orange.cost)
    Shop.Checkout(shoppingCard).cost shouldEqual expectedCostWithDiscount
  }

}
