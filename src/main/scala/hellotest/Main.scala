package hellotest

import org.log4s.*
import mainargs.{main, arg, ParserForMethods, Flag}
import scala.io.Source
import scala.collection.immutable.Map

object Main:

  @main
  def run(@arg(short = 'c', doc = "size of the sliding word cloud") cloudSize: Int = 10,
          @arg(short = 'l', doc = "minimum word length to be considered") minLength: Int = 6,
          @arg(short = 'w', doc = "size of the sliding FIFO queue") windowSize: Int = 1000,
          @arg(short = 'i', doc = "path to the ignore list file") ignoreFilePath: String = ""): Unit =
  {
    val logger = org.log4s.getLogger("logger")
    logger.debug(f"cloudSize: ${cloudSize}, minLength: ${minLength}, windowSize: ${windowSize}, ignoreFile: ${ignoreFilePath}")

    // Read the ignore list from the file if provided
    val ignoreList: Set[String] =
      if (ignoreFilePath.nonEmpty) {
        Source.fromFile(ignoreFilePath).getLines().map(_.toLowerCase.toString).toSet
      } else {
        Set.empty
      }
    logger.debug(f"ignoreElements: ${ignoreList.toString}")

    val lines = scala.io.Source.stdin.getLines()
    val words = {
      import scala.language.unsafeNulls
      lines.flatMap(l => l.split("(?U)[^\\p{Alpha}0-9']+"))
    }

    val result = run(words, Arguments(cloudSize, minLength, windowSize, ignoreFilePath))

    result
      // terminate on I/O error such as SIGPIPE
      .takeWhile: _ =>
        !scala.sys.process.stdout.checkError()
      .foreach: r =>
        println(r)
  }

  case class Arguments(
    cloudSize: Int,
    minLength: Int,
    windowSize: Int,
    ignoreFilePath: String
  )

  def run(input: Iterator[String], args: Arguments): Iterator[String] =
  {
    Iterator.empty
  }

  def main(args: Array[String]): Unit = ParserForMethods(this).runOrExit(args.toIndexedSeq)

end Main
