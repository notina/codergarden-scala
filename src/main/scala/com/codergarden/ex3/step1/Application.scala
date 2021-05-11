package com.codergarden.ex3.step1

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
    val fruits: List[Fruit] = items.flatMap(fruitName => validFruits.find(_.name == fruitName))
    val totalCost: Double = fruits.foldLeft(0D){
      case (totalCost, fruit) =>
        totalCost + fruit.cost
    }
  }


}


