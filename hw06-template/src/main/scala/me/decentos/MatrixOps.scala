package main.scala.me.decentos

abstract class MatrixOps {
  val inputFiles: Array[String]
  val outputFile: String

  final def run(): Unit = {
    input(inputFiles: Array[String])
    doOps()
    output(outputFile: String)
  }

  protected def input(inputFiles: Array[String]): Unit

  protected def doOps(): Unit

  protected def output(outputFile: String): Unit
}
