name := "sbt"

version := "1.0"

scalaVersion := "2.11.8"

// taskKey[Unit]("description") := {}

TaskKey[Unit]("hoge", "description") := {}

val piyo = taskKey[Unit]("description")
