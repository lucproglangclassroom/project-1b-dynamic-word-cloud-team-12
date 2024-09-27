package hellotest

class WordCloud(c: Int, l: Int, w: Int) {
  private val cloudSize = c
  private val minLength = l
  private val windowSize = w

  def process(input: Iterator[String], output: OutputObserver): Unit = {
    val window = scala.collection.mutable.Queue[String]() // Sliding window for the last 'windowSize' words
    val wordFrequency = scala.collection.mutable.Map[String, Int]() // To track word frequencies

    input.foreach { word =>
      // Step 1: Filter out words that are shorter than 'minLength'
      if (word.length >= minLength) {
        // Step 2: Add the word to the sliding window
        window.enqueue(word)

        // Step 3: Update the frequency map
        wordFrequency(word) = wordFrequency.getOrElse(word, 0) + 1

        // Step 4: If the window exceeds 'windowSize', remove the oldest word
        if (window.size > windowSize) {
          val oldestWord = window.dequeue() // Dequeue removes the oldest word

          // Decrease frequency of the removed word
          wordFrequency(oldestWord) -= 1
          if (wordFrequency(oldestWord) == 0) {
            wordFrequency.remove(oldestWord) // Remove the word if its count reaches zero
          }
        }

        // Only output once the window size reaches the full limit
        if (window.size == windowSize) {
          // Step 5: Sort by frequency (descending) and then alphabetical order (A-Z)
          val sortedWords = wordFrequency.toSeq
            .sortBy { case (word, count) => (-count, word) }  // Sort by count descending, then word alphabetically A-Z

          val topWordsMap = sortedWords.take(cloudSize).iterator

          // Pass the sorted word frequencies to the output observer
          output.output(topWordsMap.asInstanceOf[output.Result])
        }
      }
    }
  }
}
