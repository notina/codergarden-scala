package com.codergarden.ex3.step3

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class ApplicationSpec extends AnyFlatSpec  with ScalaCheckPropertyChecks {

  "The Checkout (offers applied) with a shoppingCard containing an empty list" should "get 0" in {
    val shoppingCard = Shop.ShoppingCart(Nil)
    Shop.Checkout(shoppingCard).cost shouldEqual 0D
  }

  "The Checkout (offers applied) with a shoppingCard containing 2 apple and 3 orange and 2 bananas" should "get the price of 1 Apple and 2 Oranges and 1 Banana" in {
    val expectedCostWithDiscount = (1 * Shop.Apple.cost) + (2 * Shop.Orange.cost) + (1 * Shop.Banana.cost)
    val shoppingCard = Shop.ShoppingCart(List("Apple","Apple","Orange","Orange","Orange","Banana","Banana"))
    Shop.Checkout(shoppingCard).cost shouldEqual expectedCostWithDiscount
  }

}
