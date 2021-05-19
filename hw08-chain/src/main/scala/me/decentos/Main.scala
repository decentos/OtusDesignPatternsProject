package main.scala.me.decentos

import scala.io.{BufferedSource, Source}

object Main extends App {

  // Разбираем параметры запуска
  if (args.length != 2) {
    println(s"Usage: inputFile outputFile")
    println("where:")
    println("  inputFile - file with the list of files to process")
    println("  outputFile - file with the result of processing")
    sys.exit(-1)
  }

  // Создаём цепочку ответственности
  val handler: Handler = new XMLHandler()
  handler.setNext(new JSONHandler()).setNext(new CSVHandler()).setNext(new TXTHandler())

  // Запускаем обработку файлов по цепочке
  if (new java.io.File(args(0)).exists) {
    val src: BufferedSource = Source.fromFile(args(0))
    try {
      src.getLines.foreach(f => handler.process(f, args(1)))
    } finally src match {
      case s: scala.io.BufferedSource => s.close
    }
  } else {
    println(s"ERROR: File ${args(0)} doesn't exist")
    sys.exit(-1)
  }
}
