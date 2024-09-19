package hellotest
import scala.collection.mutable

class ConcreteOutputObserver extends OutputObserver:
  override def output(cloud: mutable.Map[String, Int]): Unit = 
  {
    
  }

  override def convert(cloud: mutable.Map[String, Int]): String = 
  {
    ""
  }
end ConcreteOutputObserver

