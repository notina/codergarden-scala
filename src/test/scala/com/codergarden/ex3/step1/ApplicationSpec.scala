package com.codergarden.ex3.step1

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class ApplicationSpec extends AnyFlatSpec  with ScalaCheckPropertyChecks {

  "The TotalCost with an empty list" should "get 0" in {
    Shop.ShoppingCart(Nil).totalCost shouldEqual 0D
  }

  "The TotalCost with a list of 3 Apple and 1 Orange" should "get 2.05" in {
    Shop.ShoppingCart(List("Apple","Apple","Orange","Apple")).totalCost shouldEqual 2.05
  }

  "The TotalCost with a list of 3 Apple and 1 Orange containing 1 banana" should "ignore Banana and get 2.05" in {
    Shop.ShoppingCart(List("Apple","Apple","Orange","Apple","Banana")).totalCost shouldEqual 2.05
  }

}
