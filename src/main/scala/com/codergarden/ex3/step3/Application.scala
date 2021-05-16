package com.codergarden.ex3.step3

object Application {

  def main(args: Array[String]): Unit = {


    val shoppingCart = Shop.ShoppingCart(Shop.convert(args.toList))

    val apple2x1 = Shop.OneFruitFree(Shop.Apple, 2)
    val orange3x1 = Shop.OneFruitFree(Shop.Orange, 3)
    val banana2x1 = Shop.OneFruitFree(Shop.Banana, 2)
    val bananaAppleFreeBundle = Shop.cheapestBundleFree(List(Shop.Apple,Shop.Banana))

    val offers = List(apple2x1, orange3x1, banana2x1, bananaAppleFreeBundle)

    println(s"SC Total cost: £${Shop.Checkout(shoppingCart, offers).cost} " )
  }
}



