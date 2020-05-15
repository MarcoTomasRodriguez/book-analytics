package main

import io.IOUtils
import pdf.PDFUtils

import scala.util.{Failure, Success}

object Application {

  def main(args: Array[String]) {

    val fileNames = args.toSeq

    System.setProperty("java.awt.headless", "true")

    if (fileNames.length < 1) {
      IOUtils.printUsage()
      IOUtils.exit(1)
    }

    val texts = for (fileName <- fileNames) yield {
      for {
        pdf <- PDFUtils.openPdf(fileName)
        text <- PDFUtils.getTextFromPdf(pdf)
      } yield { PDFUtils.closePdf(pdf); text }
    }.getOrElse(null)


    for (text <- texts) {

      val pattern = "([a-zA-Z])\\w+".r

      val foundElements = pattern.findAllIn(text.toLowerCase).toList

      val foundElementsMapped = foundElements.groupBy(identity).view.mapValues(_.size)

      println(foundElementsMapped.toSeq.sortBy(_._2)(Ordering.Int.reverse))

    }

  }

}
