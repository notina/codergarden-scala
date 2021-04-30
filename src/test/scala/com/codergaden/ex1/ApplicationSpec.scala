package com.codergaden.ex1

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ApplicationSpec extends AnyFlatSpec with Matchers {

  "The getShorterAndLonger function with an empty list" should "get a Result containing 2 equal WordsWithLength with empty list each " in {
    Application.getShorterLonger(List()) shouldEqual Result(WordsWithLength(List()), WordsWithLength(List()))
  }

  "The getShorterAndLonger function with a list with one word" should "get a Result containing 2 equal WordsWithLength with the same length and word each " in {
    Application.getShorterLonger(List("one")) shouldEqual Result(WordsWithLength(List("one")), WordsWithLength(List("one")))
  }

  """The getShorterAndLonger function with a List("one", "three")""" should """get a Result containing WordsWithLength(List("one")), WordsWithLength(List("three") """ in {
    Application.getShorterLonger(List("one", "three")) shouldEqual Result(WordsWithLength(List("one")), WordsWithLength(List("three")))
  }

  """The getShorterAndLonger function with a List("one", "two", "three", "four")""" should """get a Result containing WordsWithLength(List("one", "two")), WordsWithLength(List("three") """ in {
    Application.getShorterLonger(List("one", "two", "three", "four")) shouldEqual Result(WordsWithLength(List("one", "two")), WordsWithLength(List("three")))
  }

  """The getShorterAndLonger function with a List("one", "two", "three", "twelve")""" should """get a Result containing WordsWithLength(List("one", "two")), WordsWithLength(List("eleven", "twelve") """ in {
    Application.getShorterLonger(List("one", "two", "eleven", "three", "four", "twelve")) shouldEqual Result(WordsWithLength(List("one", "two")), WordsWithLength(List("eleven", "twelve")))
  }

  "The getShorterAndLonger function with a list containing a word with two letters" should "get a Result that not contains that word" in {
    Application.getShorterLonger(List("as","one", "two","eleven","three","four","twelve")) shouldEqual Result(WordsWithLength(List("one", "two")),WordsWithLength(List("eleven", "twelve")))
  }

}
