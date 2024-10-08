package hellotest

import org.scalatest.funsuite.AnyFunSuite

class TestOutputObserver extends AnyFunSuite:
  test("empty iterator should be converted into empty string"):
    val outputObserver = new ConcreteOutputObserver()
    val result = outputObserver.convert(Iterator())

    assert(result == "")

  test("non-empty iterator should be converted into non-empty string"):
    val outputObserver = new ConcreteOutputObserver()
    val result = outputObserver.convert(Iterator(Tuple2("bb",2),Tuple2("aa",2),Tuple2("cc",1)))

    assert(result == "bb: 2 aa: 2 cc: 1 ")

end TestOutputObserver