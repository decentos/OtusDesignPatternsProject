package main.scala.me.decentos

class MatrixTrans(override val inputFiles: Array[String], override val outputFile: String) extends MatrixOps {
  private var matrixA: Matrix = _
  private var matrixC: Matrix = _

  override protected def input(inputFiles: Array[String]): Unit = {
    matrixA = Matrix(inputFiles(0))
  }

  override protected def doOps(): Unit = {
    matrixC = matrixA.transpose()
  }

  override protected def output(outputFile: String): Unit = {
    matrixC.save(outputFile)
  }
}
