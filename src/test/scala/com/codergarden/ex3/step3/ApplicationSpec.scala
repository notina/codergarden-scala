package com.codergarden.ex3.step3

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class ApplicationSpec extends AnyFlatSpec  with ScalaCheckPropertyChecks {

  "The Checkout (offers applied) with a shoppingCard containing an empty list" should "get 0" in {
    val shoppingCard = Shop.ShoppingCart(Nil)
    val apple2x1 = Shop.OneFruitFree(Shop.Apple, 2)
    val orange3x1 = Shop.OneFruitFree(Shop.Orange, 3)
    val banana2x1 = Shop.OneFruitFree(Shop.Banana, 2)

    val offers = List(apple2x1, orange3x1, banana2x1)
    Shop.checkout(shoppingCard, offers) shouldEqual 0D
  }

  "The Checkout (OneFruitFree offers applied)" +
    " with a shoppingCard containing 2 apple and 3 orange and 2 bananas" should "get " +
    "the price of 1 Apple and 2 Oranges and 1 Banana" in {
    val shoppingCard = Shop.ShoppingCart(Shop.convert(List("Apple","Apple","Orange","Orange","Orange","Banana","Banana")))
    val apple2x1 = Shop.OneFruitFree(Shop.Apple, 2)
    val orange3x1 = Shop.OneFruitFree(Shop.Orange, 3)
    val banana2x1 = Shop.OneFruitFree(Shop.Banana, 2)
    val offers = List(apple2x1, orange3x1, banana2x1)

    val expectedCostWithDiscount = (1 * Shop.Apple.cost) + (2 * Shop.Orange.cost) + (1 * Shop.Banana.cost)
    Shop.checkout(shoppingCard, offers) shouldEqual expectedCostWithDiscount
  }

  "The Checkout (OneFruitFree + CheapestBundleFree offers applied)" +
    " with a shoppingCard containing 2 apple and 3 orange and 2 bananas" should "get " +
    "the price of 1 Apple and 2 Oranges and 0 Banana" in {
    val shoppingCard = Shop.ShoppingCart(Shop.convert(List("Apple","Apple","Orange","Orange","Orange","Banana","Banana")))
    val apple2x1 = Shop.OneFruitFree(Shop.Apple, 2)
    val orange3x1 = Shop.OneFruitFree(Shop.Orange, 3)
    val banana2x1 = Shop.OneFruitFree(Shop.Banana, 2)
    val bananaAppleFreeBundle = Shop.CheapestBundleFree(List(Shop.Apple,Shop.Banana))
    val offers = List(apple2x1, orange3x1, banana2x1, bananaAppleFreeBundle)

    val expectedCostWithDiscount = (1 * Shop.Apple.cost) + (2 * Shop.Orange.cost)
    Shop.checkout(shoppingCard, offers) shouldEqual expectedCostWithDiscount
  }

  "The Checkout (OneFruitFree + CheapestBundleFree offers applied) " +
    "with a shoppingCard containing 2 apple and 3 orange and 50 bananas" should "get " +
    "the price of 0 Apple and 2 Oranges and 25 Banana" in {
    val fruitList: List[Shop.Fruit] = List.fill(50)(Shop.Banana) ::: List.fill(2)(Shop.Apple) ::: List.fill(3)(Shop.Orange)
    val shoppingCard = Shop.ShoppingCart(fruitList)
    val apple2x1 = Shop.OneFruitFree(Shop.Apple, 2)
    val orange3x1 = Shop.OneFruitFree(Shop.Orange, 3)
    val banana2x1 = Shop.OneFruitFree(Shop.Banana, 2)
    val bananaAppleFreeBundle = Shop.CheapestBundleFree(List(Shop.Apple,Shop.Banana))
    val offers = List(apple2x1, orange3x1, banana2x1, bananaAppleFreeBundle)

    val expectedCostWithDiscount = (2 * Shop.Orange.cost) + (25 * Shop.Banana.cost)
    Shop.checkout(shoppingCard, offers) shouldEqual expectedCostWithDiscount
  }

}
