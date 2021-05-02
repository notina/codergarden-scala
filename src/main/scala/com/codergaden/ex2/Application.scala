package com.codergaden.ex2

object Application {

  def main(args: Array[String]): Unit = {
    println(WordsCount.wordWithCount(args(0)))
  }
}

object WordsCount {
  def wordWithCount(word: String) = if (word.isEmpty) "" else s"""$word -> ${wordCounter(word).mkString("(",") (",")").replaceAll(" ->", ",")}"""

  def wordCounter(word: String) = word.toLowerCase().foldLeft(Map[Char, Int]()) {
    case (wordMap, letter) =>
      val count: Int = wordMap.getOrElse(letter, 0)
      wordMap.+(letter -> (count + 1))
  }

}



