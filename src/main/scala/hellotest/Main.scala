package hellotest

import org.log4s.*
import mainargs.{main, arg, ParserForMethods, Flag}

object Main:

  @main
  def run(@arg(short = 'c', doc = "size of the sliding word cloud") cloudSize: Int = 10,
          @arg(short = 'l', doc = "minimum word length to be considered") minLength: Int = 6,
          @arg(short = 'w', doc = "size of the sliding FIFO queue") windowSize: Int = 1000): Unit =
  {
    val logger = org.log4s.getLogger("logger")
    logger.debug(f"cloudSize: ${cloudSize}, minLength: ${minLength}, windowSize: ${windowSize}")

    val lines = scala.io.Source.stdin.getLines()

    val words = {
      import scala.language.unsafeNulls
      lines.flatMap(l => l.split("(?U)[^\\p{Alpha}0-9']+"))
    }

    val wordCloud = new WordCloud(cloudSize, minLength, windowSize)
    val outputObserver = new ConcreteOutputObserver()

    wordCloud.process(words, outputObserver)
  }

  def main(args: Array[String]): Unit = ParserForMethods(this).runOrExit(args.toIndexedSeq)

end Main
