package hellotest
import scala.collection.mutable

class ConcreteOutputObserver extends OutputObserver:
  override type Result = mutable.Map[String, Int]
  
  override def output(cloud: Result): Unit =
  {
    // DO: call convert function and print result to the standard output for the user
  }

  override def convert(cloud: Result): String =
  {
    // DO: convert the given map object into a string representation and return it
    ""
  }
end ConcreteOutputObserver

