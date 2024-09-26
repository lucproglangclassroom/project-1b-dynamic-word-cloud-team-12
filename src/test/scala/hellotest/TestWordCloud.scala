package hellotest

class WordCloud(c: Int, l: Int, w: Int) {
  private val cloudSize = c
  private val minLength = l
  private val windowSize = w

  def process(input: Iterator[String], output: OutputObserver): Unit = {
    var window = List[String]()  // Sliding window for the last 'windowSize' words
    var wordFrequency = scala.collection.mutable.Map[String, Int]()  // To track word frequencies

    input.foreach { word =>
      // Step 1: Filter out words that are shorter than 'minLength'
      if (word.length >= minLength) {
        // Step 2: Add the word to the sliding window
        window = window :+ word

        // Step 3: Update the frequency map
        wordFrequency(word) = wordFrequency.getOrElse(word, 0) + 1

        // Step 4: If the window exceeds 'windowSize', remove the oldest word
        if (window.size > windowSize) {
          val oldestWord = window.head
          window = window.tail

          // Decrease frequency of the removed word
          wordFrequency(oldestWord) -= 1
          if (wordFrequency(oldestWord) == 0) wordFrequency.remove(oldestWord) // Safe discard of Option[Int]
        }

        // Step 5: Sort by frequency and pick the top 'cloudSize' words
        val topWordsMap: scala.collection.mutable.Map[String, Int] =
          scala.collection.mutable.Map(wordFrequency.toSeq.sortBy(-_._2).take(cloudSize): _*)

        // Pass the topWordsMap to the output observer
        output.output(topWordsMap.asInstanceOf[output.Result]) // Ensure type compatibility by casting
      }
    }
  }
}
