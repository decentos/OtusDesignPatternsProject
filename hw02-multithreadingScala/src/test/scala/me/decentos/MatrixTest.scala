package test.scala.me.decentos

import org.scalatest.funsuite.AnyFunSuite
import main.scala.me.decentos.Matrix.{Compute, compute, matrixA, matrixB, matrixC}

class MatrixTest extends AnyFunSuite {
  test("Compute two 2 x 2 matrices multiplications in one thread") {
    val n: Int = 2
    matrixA = Array(Array(1, 0), Array(0, 3))
    matrixB = Array(Array(3, 0), Array(0, 2))
    val etalon = Array(Array(3, 0), Array(0, 6))
    matrixC = Array.ofDim[Int](n, n)

    for (i <- 0 until n)
      for (j <- 0 until n)
        matrixC(i)(j) = compute(i, j, matrixA, matrixB)

    assert(matrixC === etalon)
  }

  test("Compute two 3 x 3 matrices multiplications in one thread") {
    val n: Int = 3
    matrixA = Array(Array(0, 1, 0), Array(-1, 0, 0), Array(0, 0, 1))
    matrixB = Array(Array(-1, 0, 0), Array(0, 1, 0), Array(0, 0, 1))
    val etalon = Array(Array(0, 1, 0), Array(1, 0, 0), Array(0, 0, 1))
    matrixC = Array.ofDim[Int](n, n)

    for (i <- 0 until n)
      for (j <- 0 until n)
        matrixC(i)(j) = compute(i, j, matrixA, matrixB)

    assert(matrixC === etalon)
  }

  test("Compute two 2 x 2 matrices multiplications in four threads") {
    val n: Int = 2
    matrixA = Array(Array(1, 0), Array(0, 3))
    matrixB = Array(Array(3, 0), Array(0, 2))
    val etalon = Array(Array(3, 0), Array(0, 6))
    matrixC = Array.ofDim[Int](n, n)

    val thread1 = new Compute(Seq((0,0)))
    val thread2 = new Compute(Seq((0,1)))
    val thread3 = new Compute(Seq((1,0)))
    val thread4 = new Compute(Seq((1,1)))

    thread1.start()
    thread2.start()
    thread3.start()
    thread4.start()

    thread1.join()
    thread2.join()
    thread3.join()
    thread4.join()

    assert(matrixC === etalon)
  }

  test("Compute two 3 x 3 matrices multiplications in nine thread") {
    val n: Int = 3
    matrixA = Array(Array(0, 1, 0), Array(-1, 0, 0), Array(0, 0, 1))
    matrixB = Array(Array(-1, 0, 0), Array(0, 1, 0), Array(0, 0, 1))
    val etalon = Array(Array(0, 1, 0), Array(1, 0, 0), Array(0, 0, 1))
    matrixC = Array.ofDim[Int](n, n)

    val thread1 = new Compute(Seq((0,0)))
    val thread2 = new Compute(Seq((0,1)))
    val thread3 = new Compute(Seq((0,2)))
    val thread4 = new Compute(Seq((1,0)))
    val thread5 = new Compute(Seq((1,1)))
    val thread6 = new Compute(Seq((1,2)))
    val thread7 = new Compute(Seq((2,0)))
    val thread8 = new Compute(Seq((2,1)))
    val thread9 = new Compute(Seq((2,2)))

    thread1.start()
    thread2.start()
    thread3.start()
    thread4.start()
    thread5.start()
    thread6.start()
    thread7.start()
    thread8.start()
    thread9.start()

    thread1.join()
    thread2.join()
    thread3.join()
    thread4.join()
    thread5.join()
    thread6.join()
    thread7.join()
    thread8.join()
    thread9.join()

    assert(matrixC === etalon)
  }

}
