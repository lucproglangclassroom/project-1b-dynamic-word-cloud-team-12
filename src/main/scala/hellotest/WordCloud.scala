package hellotest

class WordCloud(c: Int, 
                l: Int, 
                w: Int, 
                updateFrequency: Int = 1, 
                minFrequency: Int = 1, 
                ignoreList: Set[String] = Set.empty
                ) {
  private val cloudSize = c
  private val minLength = l
  private val windowSize = w

  def process(input: Iterator[String], output: OutputObserver): Unit = {
    val window = scala.collection.mutable.Queue[String]() // Sliding window for the last 'windowSize' words
    val wordFrequency = scala.collection.mutable.Map[String, Int]() // To track word frequencies

    input.foreach { word =>
      // Step 1: Filter out words that are shorter than 'minLength' and check if it's in the ignore list
      val normalizedWord = word.toLowerCase.toString
      if (normalizedWord.length >= minLength && !ignoreList.contains(normalizedWord)) {
        // Step 2: Add the word to the sliding window
        window.enqueue(normalizedWord)
        
        // Step 3: Update the frequency map
        wordFrequency(normalizedWord) = wordFrequency.getOrElse(normalizedWord, 0) + 1

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
          // Step 5: Sort by frequency (descending) and then by word reverse alphabetically (Z-A)
          val sortedWords = wordFrequency.toSeq
            .sortWith((x, y) => x._2 > y._2 || (x._2 == y._2 && x._1 > y._1)) // Sort by count descending, then word Z-A

          val topWordsMap = sortedWords.take(cloudSize).iterator

          // Pass the sorted word frequencies to the output observer
          output.output(topWordsMap.asInstanceOf[output.Result])
        }
      }
    }
  }
}
