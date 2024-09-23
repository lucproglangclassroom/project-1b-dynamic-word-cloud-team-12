package hellotest

import org.scalatest.funsuite.AnyFunSuite

class TestOutputObserver extends AnyFunSuite:
  test("empty map should be converted into empty string"):
    val outputObserver = new ConcreteOutputObserver()
    val result = outputObserver.convert(scala.collection.mutable.Map[String,Int]())

    assert(result == "")

  test("non-empty map should be converted into non-empty string"):
    val outputObserver = new ConcreteOutputObserver()
    val result = outputObserver.convert(scala.collection.mutable.Map[String, Int](("bb", 2), ("aa", 2), ("cc",1)))

    assert(result == "bb: 2 aa: 2 cc: 1")

end TestOutputObserver