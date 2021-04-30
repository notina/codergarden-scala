package com.codergaden.ex1

object Application {

  def main(args: Array[String]): Unit = {
    val res = getShorterLonger(args.toList)
    println(
      s"""Shorter word/s: ${res.shortestWords.words.mkString(" ")} with num letters ${res.shortestWords.lengthOfWord}
      |Longer word/s: ${res.longestWords.words.mkString(" ")} with num letters ${res.longestWords.lengthOfWord}""".stripMargin )
  }

  def getShorterLonger(wordlist: List[String]): (Result) = {
    wordlist.foldLeft (Result.empty) {
      case (res@Result(shortestWords, longestWords), currWord) =>
        currWord match {
          case word if word.length < WordsWithLength.minLength => res
          case other => Result(ShortestWords(shortestWords).appendOrPut(other), LongestWords(longestWords).appendOrPut(other))
        }
    }
  }
}

case class ShortestWords(words: WordsWithLength) {
  def appendOrPut(word: String): WordsWithLength =
    word.length match {
      case shorterOf if shorterOf < words.lengthOfWord || words.isEmpty  => WordsWithLength(word :: Nil)
      case longerOf if longerOf > words.lengthOfWord => words
      case equal if equal == words.lengthOfWord => WordsWithLength(word :: words.words)
    }
}

case class LongestWords(words: WordsWithLength) {
  def appendOrPut(word: String): WordsWithLength =
    word.length match {
      case shorterOf if shorterOf < words.lengthOfWord => words
      case longerOf if longerOf > words.lengthOfWord => WordsWithLength(word :: Nil)
      case equal if equal == words.lengthOfWord => WordsWithLength(word :: words.words)
    }
}

case class WordsWithLength(words: List[String]) {
  def lengthOfWord : Int = words.headOption.getOrElse("").length
  def isEmpty : Boolean = words.isEmpty
}

object WordsWithLength {
  val empty = WordsWithLength(Nil)
  val minLength = 3
}

case class Result(shortestWords: WordsWithLength, longestWords: WordsWithLength)

object Result {
  val empty = Result(shortestWords = WordsWithLength.empty, longestWords = WordsWithLength.empty)
}
