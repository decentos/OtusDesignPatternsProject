package main.scala.me.decentos

import java.io.FileWriter

class MatrixDet(override val inputFiles: Array[String], override val outputFile: String) extends MatrixOps {
  private var matrixA: Matrix = _
  private var dt: Int = _

  override protected def input(inputFiles: Array[String]): Unit = {
    matrixA = Matrix(inputFiles(0))
  }

  override protected def doOps(): Unit = {
    dt = matrixA.det()
  }

  override protected def output(outputFile: String): Unit = {
    val fw = new FileWriter(outputFile, false)
    try {
      fw.write(s"$dt")
    }
    finally fw.close()
  }
}
