name := "book-analytics"

version := "0.1"

scalaVersion := "2.13.2"

libraryDependencies ++= Seq(
  "org.apache.pdfbox" % "pdfbox" % "1.8.2"
)

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % "0.12.3")

libraryDependencies += "com.nrinaudo" %% "kantan.csv" % "0.6.0"
