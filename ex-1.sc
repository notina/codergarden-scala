def maxLengthWord(args: List[String]) = args.maxBy(_.length).length

def minLengthWord(args: List[String]) = args.minBy(_.length).length

def filterWordsByLength(args: List[String], length: Int) = args.filter(_.length.equals(length))

def printLongerAndShorterWords(args: List[String]): Unit = {
  val filteredArgs = args.filter(_.length > 2)
  if (filteredArgs.nonEmpty) {
    val maxLength = maxLengthWord(filteredArgs)
    val minLength = minLengthWord(filteredArgs)
    
    println("Longer word/s: " + filterWordsByLength(filteredArgs, maxLength).mkString(", ") + s" with $maxLength letters")
    println("Shorter word/s: " + filterWordsByLength(filteredArgs, minLength).mkString(", ") + s" with $minLength letters")
  }
}

printLongerAndShorterWords(List("zero","one","two","four", "as"))
