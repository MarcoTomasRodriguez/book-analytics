package main

import utils.IOUtils._
import utils.FileUtils
import utils.PDFUtils
import words.Words

import scala.collection.immutable.ListMap
import scala.util.{Failure, Success}

object Application {

  def main(args: Array[String]) {

    // User should give at least two arguments (output and input)
    if (args.length < 2) { printUsage(); sys.exit() }

    // Head and tail are secure by design
    val output = args.head
    val input = args.tail.toList

    // Output file
    val outputFile = FileUtils.openFile(output)

    // Opens all the filenames and gets the text inside
    val texts = input.map(PDFUtils.getTextFromFileName(_).getOrElse(null))

    // Finds every word from every text
    val wordsFromTexts = texts.flatMap(Words.findAllWordsInText(_))

    // Group words by their identity
    val groupedWords = Words.groupWordsByIdentity(wordsFromTexts)

    // Orders the groupedWords by count (ASC)
    val orderedGroupedWords = Words.orderGroupedWords(groupedWords)(_._2 > _._2)

    // Writes the file in Json format
    val writeOp = FileUtils.writeInferredOutput(outputFile, orderedGroupedWords)

    writeOp match {
      case Success(_) => sys.exit(0)
      case Failure(ex) => { println(ex); sys.exit(1) }
    }

  }

}
