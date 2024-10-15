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

  test("when input is received, it should be filtered by length"):
    val testInput = Seq[String]("aa","b","ccc")
    val result = filterInput(testInput.iterator, Set.empty, 2)
    
    assert(result sameElements Seq("aa","ccc"))

  test("when input is received, it should be filtered by the ignore set"):
    val testInput = Seq[String]("aa","bb","c","cc","ccc","dd")
    val result = filterInput(testInput.iterator, Set("aa","cc"), 1)
    
    assert(result sameElements Seq("bb","c","ccc","dd"))

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
    val sequence = Seq("aa", "bb", "cc", "dd", "aa")
    val cloudSize = 3
    val minFrequency = 2

    val result = countFrequencies(sequence,cloudSize, minFrequency)
    
    assert(result == Map(("aa", 2)))

  test("given counts, sort them into iterator"):
    val result = sortCount(Map(("bb", 2), ("cc", 1), ("aa", 1)))
    val expected = Iterator(("bb", 2), ("aa", 1), ("cc", 1))

    assert(result sameElements expected)

  test("convert iterator into string that can be printed"):
    val result = convert(Iterator(("bb",2),("cc",1),("aa",1)))
    assert(result == "bb: 2 cc: 1 aa: 1")

  test("getValidLengthSequences should filter sequences by minimum length"):
    val input: Iterator[Seq[String]] = Iterator(
      Seq("apple", "banana", "cherry"),
      Seq("date", "fig"),
      Seq("grape", "kiwi", "lemon", "melon")
    )
    val result = getValidLengthSequences(input, 3).toList
    val expected = List(
      Seq("apple", "banana", "cherry"),
      Seq("grape", "kiwi", "lemon", "melon")
    )
    assert(result == expected)

  test("convertResults should convert word counts to string format"):
    val input: Iterator[Iterator[(String, Int)]] = Iterator(
      Iterator(
        ("apple", 3), ("banana", 2)
      ),
      Iterator(
        ("banana", 3), ("apple", 2)
      )
    )
    val result = convertResults(input)
    val expected = Iterator(
      "apple: 3 banana: 2",
      "banana: 3 apple: 2"
    )
    assert(result sameElements expected)

  test("run"):
    val result = Main.run(Iterator("a", "b", "c", "aa", "bb", "cc", "aa", "bb", "aa", "bb", "a", "b", "c", "aa", "aa", "aa"), Arguments(3, 2, 5, "", 1))
    assert(result sameElements Iterator(
      "aa: 2 bb: 2 cc: 1",
      "aa: 2 bb: 2 cc: 1",
      "aa: 2 bb: 2 cc: 1",
      "aa: 3 bb: 2",
      "aa: 3 bb: 2",
      "aa: 4 bb: 1"
    ))

end TestFunctional



