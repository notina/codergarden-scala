package com.codergarden.ex3.step2

object Application {

  def main(args: Array[String]): Unit = {
    val shoppingCart = Shop.ShoppingCart(args.toList)
    println(s"SC Total cost: £${shoppingCart.totalCost} " )
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

  case class ShoppingCart(items: List[String]) {
    val validFruits = List(Apple, Orange)
    val fruits: List[Fruit] = items.flatMap(itemName => validFruits.find(_.name == itemName))
    val totalCost: Double = fruits.foldLeft(0D){
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
    val offers = List(FruitOffer(Apple, 2), FruitOffer(Orange, 3))
    val cost = {
      offers.foldLeft(shoppingCart.totalCost) {
        case (totalCost, offer) =>
          offer.applyOffer(totalCost, shoppingCart.fruits)
      }
    }
  }



}


