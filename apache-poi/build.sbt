name := "apache-poi"

version := "1.0"

scalaVersion := "2.11.8"

resolvers ++= Seq(
  "stax" at "https://mvnrepository.com/artifact/stax/stax-api",
  "xmlbeans" at "https://mvnrepository.com/artifact/org.apache.xmlbeans/xmlbeans"
)


libraryDependencies ++= Seq(
  "org.apache.poi" % "poi" % "3.14",
  "org.apache.poi" % "poi-ooxml" % "3.14",
  "org.apache.poi" % "poi-scratchpad" % "3.14",
  "org.apache.poi" % "poi-ooxml-schemas" % "3.14"
)

    