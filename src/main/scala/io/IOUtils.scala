package io

object IOUtils {

  def exit(code: Int = 0): Unit = System.exit(code)

  def printUsage(): Unit = print(
    """
      |
      |Usage:
      | book-analytics [filename]
      |
      |""".stripMargin)

}
