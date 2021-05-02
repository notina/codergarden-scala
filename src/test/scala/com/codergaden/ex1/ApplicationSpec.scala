package com.codergaden.ex1

import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

class ApplicationSpec extends AnyFlatSpec with TypeCheckedTripleEquals {

  "The getShorterAndLonger with an empty list" should "get a Result containing 2 equal WordsWithLength with empty list each " in {
    ShortestAndLongerWords.getShorterLonger(List()) shouldEqual Result(WordsWithLength(List()), WordsWithLength(List()))
  }

  "The getShorterAndLonger with a list with one word" should "get a Result containing 2 equal WordsWithLength with the same length and word each " in {
    ShortestAndLongerWords.getShorterLonger(List("one")) shouldEqual Result(WordsWithLength(List("one")), WordsWithLength(List("one")))
  }

  """The getShorterAndLonger with a List("one", "three")""" should """get a Result containing WordsWithLength(List("one")), WordsWithLength(List("three") """ in {
    ShortestAndLongerWords.getShorterLonger(List("one", "three")) shouldEqual Result(WordsWithLength(List("one")), WordsWithLength(List("three")))
  }

  """The getShorterAndLonger with a List("one", "two", "three", "four")""" should """get a Result containing WordsWithLength(List("one", "two")), WordsWithLength(List("three") """ in {
    ShortestAndLongerWords.getShorterLonger(List("one", "two", "three", "four")) shouldEqual Result(WordsWithLength(List("two", "one")), WordsWithLength(List("three")))
  }

  """The getShorterAndLonger with a List("one", "two", "three", "twelve")""" should """get a Result containing WordsWithLength(List("one", "two")), WordsWithLength(List("eleven", "twelve") """ in {
    ShortestAndLongerWords.getShorterLonger(List("one", "two", "eleven", "three", "four", "twelve")) shouldEqual Result(WordsWithLength(List("two", "one")), WordsWithLength(List("twelve", "eleven")))
  }

  "The getShorterAndLonger with a list containing a word with two letters" should "get a Result that not contains that word" in {
    ShortestAndLongerWords.getShorterLonger(List("as","one", "two","eleven","three","four","twelve")) shouldEqual Result(WordsWithLength(List("two", "one")),WordsWithLength(List("twelve", "eleven")))
  }

}
