package hellotest
import scala.collection.mutable

class ConcreteOutputObserver extends OutputObserver:
  override def output(cloud: mutable.Map[String, Int]): Unit = 
  {
    // DO: call convert function and print result to the standard output for the user
  }

  override def convert(cloud: mutable.Map[String, Int]): String = 
  {
    // DO: convert the given map object into a string representation and return it
    ""
  }
end ConcreteOutputObserver

