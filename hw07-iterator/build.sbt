name := "hw07"

version := "1.0"

scalaVersion := "2.13.2"

lazy val root = (project in file("."))
  .settings(
    scalacOptions += "-deprecation"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.2" % "test"
