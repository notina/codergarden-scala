package com.codergaden.ex1

object Application {

  def main(args: Array[String]): Unit = {
    val res = getShorterLonger(args.toList)
    println(
      s"""Shorter word/s: ${res.shortestWords.words.mkString(" ")} with num letters ${res.shortestWords.length}
      |\nLonger word/s: ${res.longestWords.words.mkString(" ")} with num letters ${res.longestWords.length}""".stripMargin )
  }

  def getShorterLonger(wordlist: List[String]): (Result) = {

    wordlist.foldLeft (Result.empty)  {
      case (res @ Result(shortestWords, longestWords), currWord) =>

        val long = currWord.length - longestWords.length match {
          case m if m < 0 || currWord.length < WordsWithLength.minLength => WordsWithLength(longestWords.length, longestWords.words)
          case m if m > 0 => WordsWithLength(currWord.length , currWord :: Nil)
          case m if m == 0 => WordsWithLength(longestWords.length , longestWords.words :+ currWord)
        }
        val short = currWord.length - shortestWords.length match {
          case m if (m < 0 || shortestWords.words.isEmpty) && currWord.length >= WordsWithLength.minLength => WordsWithLength(currWord.length , currWord :: Nil)
          case m if m > 0 || currWord.length < WordsWithLength.minLength => WordsWithLength(shortestWords.length, shortestWords.words)
          case m if m == 0 => WordsWithLength(shortestWords.length, shortestWords.words :+ currWord)
        }
        Result(short , long)
    }
  }
}

case class WordsWithLength(length: Int, words: List[String]) {
  def append(word: String): WordsWithLength = WordsWithLength(length, word :: words)
  def put(word: String): WordsWithLength = WordsWithLength(word.length, word :: Nil)
}

object WordsWithLength {
  val empty = WordsWithLength(0, Nil)
  val minLength = 3
}

case class Result(shortestWords: WordsWithLength, longestWords: WordsWithLength)

object Result {
  val empty = Result(shortestWords = WordsWithLength.empty, longestWords = WordsWithLength.empty)
}