package utils

import java.io.{BufferedWriter, File, FileWriter}

import scala.util.Try

object FileUtils {

  def openFile(fileName: String): File = new File(fileName)

  def writeFile(file: File, content: String): Try[Unit] = Try {
    val fileWriter = new FileWriter(file)
    val bufferedWriter = new BufferedWriter(fileWriter)
    bufferedWriter.write(content)
    bufferedWriter.close()
    fileWriter.close()
  }

}
