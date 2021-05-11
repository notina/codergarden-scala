package com.codergarden.ex3.step3

import com.codergarden.ex3.step2.Shop.Checkout

object Application {

  def main(args: Array[String]): Unit = {
    val shoppingCart = Shop.ShoppingCart(args.toList)
    println(s"SC Total cost: £${Shop.Checkout(shoppingCart).cost} " )
  }
}

object Shop {

  trait Fruit {
    val cost: Double
    val name: String
  }

  object Apple extends Fruit {
    val cost = 0.60
    val name = "Apple"
  }

  object Orange extends Fruit {
    val cost = 0.25
    val name = "Orange"
  }

  object Banana extends Fruit {
    val cost = 0.20
    val name = "Banana"
  }

  case class ShoppingCart(items: List[String]) {
    val validFruits = List(Apple, Orange, Banana)
    val fruits: List[Fruit] = items.flatMap(fruitName => validFruits.find(_.name == fruitName))
    val totalCost = fruits.foldLeft(0D) {
      case (totalCost, fruit) =>
        totalCost + fruit.cost
    }
  }

  case class FruitOffer(fruit: Fruit, minimalFruitsForOffer: Int)  {
    def applyOffer(cost: Double, fruits: List[Fruit]): Double = {
      val numberOfFreeFruit = fruits.count(_ == fruit) / minimalFruitsForOffer
      val discount = numberOfFreeFruit * fruit.cost
      cost - discount
    }
  }

  case class Checkout(shoppingCart: ShoppingCart) {

    val offers = List(FruitOffer(Apple, 2), FruitOffer(Orange, 3), FruitOffer(Banana, 2))

    val cost = {
      offers.foldLeft(shoppingCart.totalCost) {
        case (totalCost, offer) =>
          offer.applyOffer(totalCost, shoppingCart.fruits)
      }
    }
  }



}


