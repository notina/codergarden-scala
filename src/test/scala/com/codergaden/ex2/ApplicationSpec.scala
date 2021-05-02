package com.codergaden.ex2

import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

class ApplicationSpec extends AnyFlatSpec with TypeCheckedTripleEquals {

  "The wordCounter with an empty string" should "get an empty Map" in {
    WordsCount.wordCounter("") shouldEqual Map()
  }

  "The wordCounter with a string containing more occurrence of each letter" should "get a Map containing word's unique letter and its occurrence " in {
    WordsCount.wordCounter("Giorgio") shouldEqual Map('g' -> 2, 'i' -> 2, 'o' -> 2, 'r' -> 1)
  }

  "The wordWithCount with an empty string"  should "get an empty String " in {
    WordsCount.wordWithCount(("")) shouldEqual ""
  }

  "The wordWithCount with a string containing more occurrence of each letter" should "get a String containing the word and all word's unique letters and their occurrences " in {
    WordsCount.wordWithCount(("Giorgio")) shouldEqual "Giorgio -> (g, 2) (i, 2) (o, 2) (r, 1)"
  }

  "Given a string to the wordCounter, the length of the string" should " equal to the sum of all the occurrences in the Map " in {
    "Giorgio".length shouldEqual WordsCount.wordCounter("Giorgio").foldLeft(0.0)(_+_._2)
  }

}
