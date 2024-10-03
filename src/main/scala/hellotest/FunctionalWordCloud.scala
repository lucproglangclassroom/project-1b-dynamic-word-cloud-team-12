package hellotest

def readIgnoreFile(ignoreFilePath: String): Set[String] = {
  // DO: move the code for reading the ignore file content into function.
  // Also, close source and remove redundant toString
  Set.empty
}

def accumulateSequence(seq: Seq[String], next: String, windowSize: Int): Seq[String] = {
  // DO: accumulate sequence.
  // ex: [1,2,3],4,5 => [1,2,3,4] | [1,2,3,4,5],6,5 => [2,3,4,5,6]
  Seq.empty
}

def countFrequencies(seq: Seq[String], cloudSize: Int): Map[String, Int] = 
{
  // DO: return a map that counts how many times each string appears in the
  Map.empty
}

def sortCount(map: Map[String,Int]): Iterator[(String,Int)] = 
{
  // DO: sort the map and return as iterator
  // ex: Map(("aa",1),("bb",2)) => Iterator(("bb",2),("aa",1))
  Iterator.empty
}

def convert(wordCount: Iterator[(String, Int)]): String = {
  // DO: Take in iterator of word counts and convert into a string (ex: Iterator(("bb",2),("aa",2)) -> "bb: 2 aa: 2")
  ""
}