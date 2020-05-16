package main

import io.circe.syntax._
import utils.IOUtils._
import utils.FileUtils
import utils.PDFUtils

import scala.collection.immutable.ListMap

object Application {

  def main(args: Array[String]) {

    // User should give at least two arguments (output and input)
    if (args.length < 2) { printUsage(); sys.exit() }

    // Head and tail are secure by design
    val output = args.head
    val input = args.tail

    // Output file
    val outputFile = FileUtils.openFile(output)

    // Opens all the filenames and gets the text inside
    val texts = input.map(PDFUtils.getTextFromFileName(_).getOrElse(null))

    // Match all the words (a-zA-Z)
    val pattern = "([a-zA-Z])\\w+".r

    // Finds every word from every text
    val wordsFromTexts = texts.flatMap(text => pattern.findAllIn(text.toLowerCase))

    // Group words by their identity
    val groupedWords = wordsFromTexts.groupBy(identity).view.mapValues(_.size)

    // Orders the groupedWords by count (ASC)
    val orderedGroupedWords = ListMap(groupedWords.toSeq.sortWith(_._2 > _._2): _*)

    // Writes the file in Json format
    val writeOp = FileUtils.writeFile(outputFile, orderedGroupedWords.asJson.toString)

    sys.exit(if (writeOp.isSuccess) 0 else 1)

  }

}
