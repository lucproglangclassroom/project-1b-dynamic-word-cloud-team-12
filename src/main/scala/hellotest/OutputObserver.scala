package hellotest

trait OutputObserver {
  type Result

  def output(result: Result): Unit
  
  def convert(result: Result): String
}
