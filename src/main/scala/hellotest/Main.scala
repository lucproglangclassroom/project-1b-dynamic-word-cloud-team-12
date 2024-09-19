package hellotest

object Main:

  def main(args: Array[String]) =
    val lines = scala.io.Source.stdin.getLines()

    val words = {
      import scala.language.unsafeNulls
      lines.flatMap(l => l.split("(?U)[^\\p{Alpha}0-9']+"))
    }

    for (word <- words)
    {
      println("word: " + word)
      if scala.sys.process.stdout.checkError() then sys.exit(1)
    }

end Main
