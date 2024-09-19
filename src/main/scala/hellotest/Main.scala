package hellotest

import org.log4s.*
import org.log4s.Logger.DebugLevelLogger

object Main:

  def main(args: Array[String]): Unit =
    val logger = org.log4s.getLogger("logger")
    logger.debug(f"argument count: ${args.length}")

    val lines = scala.io.Source.stdin.getLines()

    val words = {
      import scala.language.unsafeNulls
      lines.flatMap(l => l.split("(?U)[^\\p{Alpha}0-9']+"))
    }

    for (word <- words)
    {
      println("word: " + word)
      if scala.sys.process.stdout.checkError() then
        sys.exit(1)
    }

end Main
