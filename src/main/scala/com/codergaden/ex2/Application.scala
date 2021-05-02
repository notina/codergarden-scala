package com.codergaden.ex2

/*
    given a string as input
    print the letter contained in the string + the number of times it appears in the word
    i.e. Giorgio ->
    (g, 2), (i, 2), (o, 2) (r, 1)
 */

object Application {

  def main(args: Array[String]): Unit = {
    println(wordWithCount(args(0)))
  }

  def wordWithCount(word: String) = if (word.isEmpty) "" else s"""$word -> ${wordCounter(word).mkString("(",") (",")")}"""

  def wordCounter(word: String) = word.toLowerCase().foldLeft(Map[Char, Int]()) {
    case (wordMap, letter) =>
      val count: Int = wordMap.getOrElse(letter, 0)
      wordMap.+(letter -> (count + 1))
  }

}



