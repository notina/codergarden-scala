def examWords(args: List[String]): Unit = {
  val filteredArgs = args.filter(_.length > 2)
  if (filteredArgs.nonEmpty) {
    val maxWord = filteredArgs.maxBy(_.length)
    val minWord = filteredArgs.minBy(_.length)

    print("Longer word/s: ")
    print(filteredArgs.filter(_.length.equals(maxWord.length)).mkString(", "))
    print(s" with ${maxWord.length} letters")

    println
    
    print("Shorter word/s: ")
    print(filteredArgs.filter(_.length.equals(minWord.length)).mkString(", "))
    print(s" with ${minWord.length} letters")
  }
}


examWords(List("zero","one","two","four", "as"))
