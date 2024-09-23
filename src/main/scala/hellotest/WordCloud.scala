package hellotest

class WordCloud(c: Int, l: Int, w: Int) {
  private val cloudSize = c
  private val minLength = l
  private val windowSize = w

  def process(input: Iterator[String], output: OutputObserver): Unit = 
  {
    // DO: create word cloud from given iterator and call observer so it can display result to user
  }
}
