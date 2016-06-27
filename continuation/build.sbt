name := "apache-poi"

version := "1.0"

scalaVersion := "2.11.8"

addCompilerPlugin("org.scala-lang.plugins" % "scala-continuations-plugin" % "1.0.2" cross CrossVersion.full)

scalacOptions += "-P:continuations:enable"

libraryDependencies += "org.scala-lang.plugins" %% "scala-continuations-library" % "1.0.2"

libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.4"
