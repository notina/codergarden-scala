package com.codergarden.ex3.step3

object Shop {

  val validFruits = List(Apple, Orange, Banana)

  def convert(fruits: List[String]): List[Fruit] =
    fruits.flatMap(fruit => validFruits.find(_.name == fruit))

  sealed trait Fruit {
    val cost: BigDecimal
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

  case class ShoppingCart(fruits: List[Fruit]) {
    def totalCost: BigDecimal = fruits.foldLeft(BigDecimal(0)) {
      case (totalCost, fruit) =>
        totalCost + fruit.cost
    }

    def totalCostByFruit: Map[Fruit, BigDecimal] = fruits.foldLeft(Map[Fruit, BigDecimal]()) {
      case (fruitsMap, fruit) =>
        val subTotal: BigDecimal = fruitsMap.getOrElse(fruit, 0)
        fruitsMap + (fruit -> (subTotal + fruit.cost))
    }
  }

  sealed trait Offer { def applyTo(shoppingCart: ShoppingCart): ShoppingCart }

  case class OneFruitFree(fruitWithOffer: Fruit, minimalFruitsForOffer: Int) extends Offer  {
    def applyTo(shoppingCart: ShoppingCart): ShoppingCart = {
      val numberOfFreeFruits = shoppingCart.fruits.count(_ == fruitWithOffer) / minimalFruitsForOffer
      ShoppingCart( shoppingCart.fruits diff List.fill(numberOfFreeFruits)(fruitWithOffer) )
    }
  }

 case class CheapestBundleFree(fruitBundlesWithOffer: List[Fruit]) extends Offer  {
    def applyTo(shoppingCart: ShoppingCart ): ShoppingCart = {
      val filteredBundles = shoppingCart.fruits.flatMap(fruit => fruitBundlesWithOffer.find(_ == fruit))
      val cheapestFruitBundle: Fruit = ShoppingCart(filteredBundles).totalCostByFruit.minBy{ case(fruit, cost) => cost}._1
      ShoppingCart( shoppingCart.fruits.filter(_ != cheapestFruitBundle) )
    }
  }

  def checkout(shoppingCart: ShoppingCart, offers: List[Offer]) = {
      offers.foldLeft(shoppingCart) {
        case (shoppingCart, offer) =>
          offer.applyTo(shoppingCart)
      }.totalCost
  }
}
