package hellotest

trait OutputObserver {
  def output(cloud: scala.collection.mutable.Map[String,Int]): Unit
  
  def convert(cloud: scala.collection.mutable.Map[String, Int]): String
}
