package main

import io.circe.syntax._
import utils.{FileUtils, IOUtils, PDFUtils}

object Application {

  def main(args: Array[String]) {

    System.setProperty("java.awt.headless", "true")

    if (args.length < 2) { IOUtils.printUsage(); IOUtils.exit() }

    // Secure by design
    val output = args.head
    val outputFile = FileUtils.openFile(output)
    val input = args.tail

    val texts = for (fileName <- input) yield {
      for {
        pdf <- PDFUtils.openPdf(fileName)
        text <- PDFUtils.getTextFromPdf(pdf)
      } yield { PDFUtils.closePdf(pdf); text }
    }.getOrElse(null)

    val pattern = "([a-zA-Z])\\w+".r
    val extractedWords = for (text <- texts) yield pattern.findAllIn(text.toLowerCase).toList

    val result = extractedWords.flatMap(_.groupBy(identity).view.mapValues(_.size)).toMap

    val writeOp = FileUtils.writeFile(outputFile, result.asJson.toString)

    IOUtils.exit(if (writeOp.isSuccess) 0 else 1)

  }

}
