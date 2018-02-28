organization := "com.phenan"

name := "scalalrExamples"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "com.phenan" %% "scalalr" % "2.3.2",
  "org.json4s" %% "json4s-native" % "3.5.3",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked",
  "-Xlint",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-unused",
  "-Ywarn-unused-import",
  "-Ywarn-value-discard"
)
