package hellotest

import hellotest.Main.Arguments
import org.scalatest.funsuite.AnyFunSuite

class TestFunctional extends AnyFunSuite:
  test("if no ignore file is specified, then it should create an empty ignore set"):
    val result = readIgnoreFile("")
    assert(result == Set.empty)

  test("when an ignore file is specified, it should create a non-empty ignore set"):
    val result = readIgnoreFile("src/test/scala/hellotest/test_ignore.txt")
    assert(result == Set("aa","bb"))

  test("if window size of sequence is not reached, then append next element"):
    val sequence = Seq.empty
    val next = "word"
    val windowSize = 5
    
    val result = accumulateSequence(sequence,next,windowSize)
    
    assert(result == Seq("word"))

  test("if window size of sequence is reached, then remove last element and append next one"):
    val sequence = Seq("a","b","c")
    val next = "d"
    val windowSize = 3
    
    val result = accumulateSequence(sequence, next, windowSize)
    
    assert(result == Seq("b","c","d"))

  test("given sequence of words, create map of their counts"):
    val sequence = Seq("aa","bb","cc","dd")
    val cloudSize = 3
    
    val result = countFrequencies(sequence,cloudSize)
    
    assert(result == Map(("bb",2),("cc",1),("aa",1)))

  test("given counts, sort them into iterator"):
    val result = sortCount(Map(("bb",2),("cc",1),("aa",1)))
    assert(result sameElements Iterator(("bb",2),("cc",1),("aa",1)))

  test("convert iterator into string that can be printed"):
    val result = convert(Iterator(("bb",2),("cc",1),("aa",1)))
    assert(result == "bb: 2 cc: 1 aa: 1")

end TestFunctional



