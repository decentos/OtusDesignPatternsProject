package main.scala.me.decentos

object Main {
  def main(args: Array[String]): Unit = {
    // Разбираем параметры запуска
    if (args.length != 3) {
      println(s"Usage: from to outputFile")
      println("where:")
      println("  from - index of the beginning of the Fibonacci sequence")
      println("  to  - index of the end of the Fibonacci sequence")
      println("  outputFile - file with the Fibonacci sequence")
      sys.exit(-1)
    }

    val from: Int = parseParameter(args(0))
    val to: Int = parseParameter(args(1))

    // Создаём агрегатор для последовательности Фибонначи
    val aggregator: FibonacciAggregator =
      if (to >= from) new FibonacciForwardAggregator(Fibonacci(from), Fibonacci(to))
      else new FibonacciBackwardAggregator(Fibonacci(from), Fibonacci(to))

    val iterator: Iterator[Fibonacci] = aggregator.iterator

    while (iterator.hasNext) {
      iterator.current.writeValue(args(2))
      iterator.next()
    }
  }

  // Функция разбора цифрового параметра
  def parseParameter(p: String): Int = {
    val n: Int =
      try {
        p.toInt
      } catch {
        case _: Throwable => exitError("couldn't parse parameter")
          0
      }
    if (n < 0) exitError("index should be positive")
    n
  }

  // Функция сообщает об ошибке и завершает работу
  def exitError(e: String): Unit = {
    println("ERROR: " + e)
    sys.exit(-1)
  }

}
