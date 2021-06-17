package main.scala.me.decentos

import scala.io._

object Matrix {
  // Класс для вычисления произведение в потоке
  // Получает список ячеек, которые надо вычислить
  class Compute(cells: Seq[(Int, Int)]) extends Thread {
    override def run(): Unit = {
      cells foreach { case (i, j) => matrixC(i)(j) = compute(i, j, matrixA, matrixB) }
    }
  }

  // Исходные и целевая матрицы
  var matrixA: Array[Array[Int]] = Array[Array[Int]]()
  var matrixB: Array[Array[Int]] = Array[Array[Int]]()
  var matrixC: Array[Array[Int]] = Array[Array[Int]]()

  def main(args: Array[String]): Unit = {

    // Разбираем параметры запуска
    if (args.length != 6) {
      println(s"Usage: -n N -p P file1 file2")
      println("where:")
      println("      -n N  - matrices dimension")
      println("      -p P  - number of threads")
      println("      file1 - file with the first matrix data")
      println("      file1 - file with the second matrix data")
      sys.exit(-1)
    }

    val n: Int = parseParameter(args(1))  // Размерность матриц
    val p: Int = math.min(n * n, parseParameter(args(3)))  // Количество потоков не может быть больше кол-ва ячеек

    // Заполняем исходные матрицы данными из файлов
    matrixA = readMatrix(n, args(4))
    matrixB = readMatrix(n, args(5))
    matrixC = Array.ofDim[Int](n, n)

    // Выводим исходные матрицы
    println("Matrix A")
    printMatrix(n, matrixA)

    println("\nMatrix B")
    printMatrix(n, matrixB)

    if (p > 1) {
      println("Вычисляем произведение в несколько потоков")

      // Список ячеек для вычислений
      val cells: Seq[(Int, Int)] = (0 until n).flatMap { i => (0 until n) map { j => (i, j) } }
      // Количество ячеек на один поток
      val nByP: Int =  math.ceil((n * n) / p.toFloat).toInt

      // Подготавливаем p потоков, каждый рассчитает не более nByP ячеек
      val threads: Seq[Compute] = (0 until p) map { pn =>
        cells.slice(pn * nByP, (pn + 1) * nByP)
      } map { c => new Compute(c) }

      // Запускаем потоки и ждём их окончания
      threads.foreach(_.start())
      threads.foreach(_.join())

    } else {
      println("Вычисляем произведение в один поток")

      for (i <- 0 until n)
        for (j <- 0 until n)
          matrixC(i)(j) = compute(i, j, matrixA, matrixB)
    }

    // Выводим целевую матрицу
    println("\nMatrix C = A * B")
    printMatrix(n, matrixC)
  }

  // Вычисляем произведение матриц обычным однопоточным методом
  def compute(i: Int, j: Int, a: Array[Array[Int]], b: Array[Array[Int]]): Int = {
    val ai: Array[Int] = a(i)
    val bj: Array[Int] = b.map(_(j))
    (ai, bj).zipped.map { case (x, y) => x * y }.sum
  }

  // Функция вывода матрицы
  def printMatrix(n: Int, a: Array[Array[Int]]): Unit = {
    for (i <- 0 until n) {
      for (j <- 0 until n)
        print(s"${a(i)(j)} ")
      println()
    }
  }

  // Функция разбора цифрового параметра
  def parseParameter(p: String): Int = {
    val n: Int =
      try {
        p.toInt
      } catch {
        case _: Throwable => exitError("couldn't parse matrices dimension")
          0
      }
    if (n < 0) exitError("incorrect matrices dimension")
    n
  }

  // Функция сообщает об ошибке и завершает работу
  def exitError(e: String): Unit = {
    println("ERROR: " + e)
    sys.exit(-1)
  }

  // Функция чтения матрицы из файла
  def readMatrix(n: Int, filename: String): Array[Array[Int]] = {
    val m: Array[Array[Int]] = Array.ofDim[Int](n, n)
    if (new java.io.File(filename).exists) {
      val src: BufferedSource = Source.fromFile(filename)
      try {
        var r: Int = 0
        src.getLines.foreach { line =>
          if (r < n) {
            val row: Array[Int] = line.trim.replaceAll(" +", " ").split(" ").map(_.toInt)
            m(r) = row.slice(0, n)
          }
          r = r + 1
        }
      } finally src match {
        case s: scala.io.BufferedSource => s.close
      }
    }
    else {
      exitError(s"File $filename doesn't exist")
    }
    m
  }
}
