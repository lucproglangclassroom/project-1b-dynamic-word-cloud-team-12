name := "hello-scalatest-scala"

version := "0.3"

scalaVersion := "3.3.3"

scalacOptions += "@.scalacOptions.txt"

libraryDependencies ++= Seq(
  "org.scalatest"  %% "scalatest"  % "3.2.19"  % Test,
  "org.scalacheck" %% "scalacheck" % "1.18.1" % Test,
  "org.slf4j" % "slf4j-simple" % "2.0.13",
  "org.log4s" %% "log4s" % "1.10.0",
  "com.lihaoyi" %% "mainargs" % "0.7.5",
)

enablePlugins(JavaAppPackaging)
