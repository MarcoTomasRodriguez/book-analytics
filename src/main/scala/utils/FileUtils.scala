package utils

import java.io.{BufferedWriter, File, FileWriter}

import io.circe.syntax.EncoderOps
import kantan.csv.rfc
import kantan.csv.ops._

import scala.collection.immutable.ListMap
import scala.util.Try

object FileUtils {

  type Output = ListMap[String, Int]

  def getFileExtension(file: File): String = {
    val fileName = file.getName
    val lastDot = fileName.lastIndexOf(".")
    if (lastDot != -1 && lastDot != 0) fileName.substring(lastDot + 1)
    else ""
  }

  def openFile(fileName: String): File = new File(fileName)

  def writeFile(file: File, content: String): Try[Unit] = Try {
    val fileWriter = new FileWriter(file)
    val bufferedWriter = new BufferedWriter(fileWriter)
    bufferedWriter.write(content)
    bufferedWriter.close()
    fileWriter.close()
  }

  def writeJsonOutput(file: File, content: Output): Try[Unit] = Try {
    FileUtils.writeFile(file, content.asJson.toString)
  }

  def writeCsvOutput(file: File, content: Output): Try[Unit] = Try {
    FileUtils.writeFile(file, content.asCsv(rfc.withHeader("word", "count")))
  }

  def writeInferredOutput(file: File, content: Output): Try[Unit] = Try {
    getFileExtension(file) match {
      case "json" => writeJsonOutput(file, content)
      case "csv" => writeCsvOutput(file, content)
      case extension =>
        throw new IllegalArgumentException(s"Extension: '$extension' is not supported")
    }
  }

}
