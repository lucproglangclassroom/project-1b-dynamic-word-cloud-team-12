package hellotest

import scala.io.Source
import scala.util.{Try,Success,Failure}

def readIgnoreFile(ignoreFilePath: String): Set[String] = {
  // DO: move the code for reading the ignore file content into function.
  // Also, close source and remove redundant toString
  Try {
    import scala.language.unsafeNulls
    val file = Source.fromFile(ignoreFilePath);
    val output = file.getLines().flatMap(l => l.split("(?U)[^\\p{Alpha}0-9']+")).toSet;
    file.close();
    output
  } match {
    case Success(ignored) => ignored
    case Failure(exception) => Set.empty
  }
}

def filterInput(input: Iterator[String], ignoreList: Set[String], minLength: Int): Iterator[String] = {
  input.filterNot(word => {ignoreList(word) || word.length < minLength})
}

def accumulateSequence(seq: Seq[String], next: String, windowSize: Int): Seq[String] = {
  // DO: accumulate sequence.
  // ex: [1,2,3],4,5 => [1,2,3,4] | [1,2,3,4,5],6,5 => [2,3,4,5,6]
  if (seq.length < windowSize) {
    seq :+ next
  } else {
    seq.drop(1) :+ next
  }
}

def countFrequencies(seq: Seq[String], cloudSize: Int, minFrequency: Int): Map[String, Int] = 
{
  // DO: return a map that counts how many times each string appears in the
    // Count frequencies of each word
    val wordCounts = seq
        .groupBy(identity)
        .view
        .mapValues(_.size)
        .toMap
        .filter { case (_, count) => count >= minFrequency } // Filter words based on the minimum frequency

    wordCounts.toSeq
        .sortBy { case (word, count) => (-count, word) } // Sort by count descending, then word ascending
        .take(cloudSize) // Cut to the cloud size
        .toMap // Convert back to Map
}

def sortCount(map: Map[String,Int]): Iterator[(String,Int)] = 
{
    // DO: sort the map and return as iterator
    map.toSeq.sortBy { 
        case (word, count) => (-count, word) // Sort by count (descending) and then by word (ascending)
    }.iterator
}

def convert(wordCount: Iterator[(String, Int)]): String = {
  // DO: Take in iterator of word counts and convert into a string (ex: Iterator(("bb",2),("aa",2)) -> "bb: 2 aa: 2")
  wordCount.map { case (word, count) => s"$word: $count" }.mkString(" ")
}

def getValidLengthSequences(sequences: Iterator[Seq[String]], minLength: Int): Iterator[Seq[String]] = {
  sequences.filter(seq => seq.length >= minLength)
}

def mapWordCounts(sequences: Iterator[Seq[String]], cloudSize: Int, minFrequency: Int): Iterator[Map[String, Int]] = {
  sequences.map(sequence => countFrequencies(sequence, cloudSize, minFrequency))
}

def sortWordCounts(wordCounts: Iterator[Map[String, Int]]): Iterator[Iterator[(String, Int)]] = {
  wordCounts.map(sortCount)
}

def convertResults(sortedCounts: Iterator[Iterator[(String, Int)]]): Iterator[String] = {
  sortedCounts.map(count => convert(count))
} 