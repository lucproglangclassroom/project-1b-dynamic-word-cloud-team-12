package hellotest

import org.log4s.*
import mainargs.{main, arg, ParserForMethods, Flag}
import scala.io.Source

object Main:

  @main
  def run(@arg(short = 'c', doc = "size of the sliding word cloud") cloudSize: Int = 10,
          @arg(short = 'l', doc = "minimum word length to be considered") minLength: Int = 6,
          @arg(short = 'w', doc = "size of the sliding FIFO queue") windowSize: Int = 1000,
          @arg(short = 'k', doc = "update frequency for the word cloud") updateFrequency: Int = 1,
          @arg(short = 'f', doc = "minimum frequency to include a word in the word cloud") minFrequency: Int = 1,
          @arg(doc = "path to the ignore list file") ignoreFilePath: String = ""): Unit =
  {
    val logger = org.log4s.getLogger("logger")
    logger.debug(f"cloudSize: ${cloudSize}, minLength: ${minLength}, windowSize: ${windowSize}, updateFrequency: ${updateFrequency}, minFrequency: ${minFrequency}, ignoreFile: ${ignoreFilePath}")

    // Read the ignore list from the file if provided
    val ignoreList: Set[String] =
      if (ignoreFilePath.nonEmpty) {
        Source.fromFile(ignoreFilePath).getLines().map(_.toLowerCase.toString).toSet
      } else {
        Set.empty
      }

    val lines = scala.io.Source.stdin.getLines()
    val words = {
      import scala.language.unsafeNulls
      lines.flatMap(l => l.split("(?U)[^\\p{Alpha}0-9']+"))
    }

    val wordCloud = new WordCloud(cloudSize, minLength, windowSize, updateFrequency, minFrequency, ignoreList)
    val outputObserver = new ConcreteOutputObserver()

    wordCloud.process(words, outputObserver)
  }

  def main(args: Array[String]): Any = ParserForMethods(this).runOrExit(args.toIndexedSeq)

end Main
