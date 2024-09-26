package hellotest

import org.scalatest.funsuite.AnyFunSuite
import scala.collection.mutable


class TestWordCloud extends AnyFunSuite:
  test("should have an empty count"):
    val wordCloud = new WordCloud(10, 6, 1000)
    val input = List()
    val output = new OutputToList()

    wordCloud.process(input.iterator, output)

    assert(output.list.isEmpty)

  test("should have a non-empty count"):
    val wordCloud = new WordCloud(3, 2, 5)
    val input = List("a", "b", "c", "aa", "bb", "cc", "aa", "bb", "aa", "bb", "a", "b", "c", "a", "a", "a")
    val output = new OutputToList()

    wordCloud.process(input.iterator, output)

    assert(output.list(0) == mutable.Map[String, Int](("bb", 2), ("aa", 2), ("cc", 1)))
    assert(output.list(1) == mutable.Map[String, Int](("bb", 2), ("aa", 2), ("cc", 1)))
    assert(output.list(2) == mutable.Map[String, Int](("bb", 2), ("aa", 2), ("cc", 1)))
    assert(output.list(3) == mutable.Map[String, Int](("aa", 3), ("bb", 2)))
    assert(output.list(4) == mutable.Map[String, Int](("aa", 3), ("bb", 2)))
    assert(output.list(5) == mutable.Map[String, Int](("aa", 4), ("bb", 1)))
end TestWordCloud

class OutputToList extends OutputObserver:
  override type Result = mutable.Map[String,Int]

  val list: mutable.Queue[mutable.Map[String, Int]] = mutable.Queue[mutable.Map[String, Int]]()

  override def output(cloud: Result): Unit =
  {
    list.append(cloud.clone())
  }

  override def convert(cloud: Result): String =
  {
    ""
  }
end OutputToList