package utils

import java.io.File

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.util.PDFTextStripper

import scala.util.Try

object PDFUtils {

  def openPdf(fileName: String): Try[PDDocument] = Try {
    PDDocument.load(new File(fileName))
  }

  def closePdf(pdf: PDDocument): Try[Unit] = Try {
    pdf.close()
  }

  def getTextFromPdf(pdf: PDDocument): Try[String] = Try{
    new PDFTextStripper().getText(pdf)
  }

}
