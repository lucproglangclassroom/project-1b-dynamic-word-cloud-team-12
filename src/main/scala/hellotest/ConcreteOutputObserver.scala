package hellotest
import scala.collection.mutable

class ConcreteOutputObserver extends OutputObserver:
  override type Result = mutable.Map[String, Int]
  
  override def output(cloud: Result): Unit =
  {
    // DO: call convert function and print result to the standard output for the user
    println(convert(cloud))
  }

  override def convert(cloud: Result): String =
  {
    // DO: convert the given map object into a string representation and return it
    val keys = cloud.toSeq.sortBy(_._2).reverse.iterator
    if (!keys.hasNext) return ""
    var pair = keys.next()
    var output = pair._1 + ": " + pair._2
    while (keys.hasNext)
      pair = keys.next()
      output += " " + pair._1 + ": " + pair._2
    return output
  }
end ConcreteOutputObserver

