package test.scala.me.decentos

import java.io.File

import main.scala.me.decentos.{CSVHandler, Handler, JSONHandler, TXTHandler, XMLHandler}
import org.scalatest.funsuite.AnyFunSuite

import scala.io.{BufferedSource, Source}

class HandlerTest extends AnyFunSuite {

  val logFilename: String = "ChainOfResponsibility.log"

  // Создаём цепочку ответственности
  val handler: Handler = new XMLHandler()
  handler.setNext(new JSONHandler()).setNext(new CSVHandler()).setNext(new TXTHandler())

  test("Test XML Handler") {
    if (new java.io.File(logFilename).exists)
      new File(logFilename).delete()

    handler.process("data/xmlfile1.xml", "data/Test1Output.txt")

    val src: BufferedSource = Source.fromFile(logFilename)
    val logLines: Int =
      try {
        src.getLines.length
      } finally src.close

    assert(logLines === 1)
  }

  test("Test JSON Handler") {
    if (new java.io.File(logFilename).exists)
      new File(logFilename).delete()

    handler.process("data/jsonfile1.json", "data/Test2Output.txt")

    val src: BufferedSource = Source.fromFile(logFilename)
    val logLines: Int =
      try {
        src.getLines.length
      } finally src.close

    assert(logLines === 1)
  }

  test("Test CSV Handler") {
    if (new java.io.File(logFilename).exists)
      new File(logFilename).delete()

    handler.process("data/csvfile1.csv", "data/Test3Output.txt")

    val src: BufferedSource = Source.fromFile(logFilename)
    val logLines: Int =
      try {
        src.getLines.length
      } finally src.close

    assert(logLines === 1)
  }

  test("Test TXT Handler") {
    if (new java.io.File(logFilename).exists)
      new File(logFilename).delete()

    handler.process("data/txtfile1.txt", "data/Test4Output.txt")

    val src: BufferedSource = Source.fromFile(logFilename)
    val logLines: Int =
      try {
        src.getLines.length
      } finally src.close

    assert(logLines === 1)
  }

}
