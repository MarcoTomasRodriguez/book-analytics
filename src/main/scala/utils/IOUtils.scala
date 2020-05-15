package utils

object IOUtils {

  def exit(code: Int = 0): Unit = System.exit(code)

  def printUsage(): Unit = print(
    """
      |Book Analytics
      |
      |Usage:
      | book-analytics output [input]
      |
      |""".stripMargin)

}
