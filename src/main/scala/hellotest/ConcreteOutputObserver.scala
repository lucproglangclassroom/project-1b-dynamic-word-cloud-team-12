package hellotest
import scala.collection.mutable

class ConcreteOutputObserver extends OutputObserver:
  override type Result = Iterator[Tuple2[String,Int]]
  
  override def output(cloud: Result): Unit =
  {
    // DO: call convert function and print result to the standard output for the user
    println(convert(cloud))
  }

  override def convert(cloud: Result): String =
  {
    // DO: convert the given map object into a string representation and return it
    var output = ""
    for (entry <- cloud) output += entry._1 + ": " + entry._2 + " "
    return output
  }
end ConcreteOutputObserver

