package main.scala.me.decentos

class MatrixMult(override val inputFiles: Array[String], override val outputFile: String) extends MatrixOps {
  private var matrixA: Matrix = _
  private var matrixB: Matrix = _
  private var matrixC: Matrix = _

  override protected def input(inputFiles: Array[String]): Unit = {
    matrixA = Matrix(inputFiles(0))
    matrixB = Matrix(inputFiles(1))
  }

  override protected def doOps(): Unit = {
    matrixC = matrixA.multiply(matrixB, 1)
  }

  override protected def output(outputFile: String): Unit = {
    matrixC.save(outputFile)
  }
}
