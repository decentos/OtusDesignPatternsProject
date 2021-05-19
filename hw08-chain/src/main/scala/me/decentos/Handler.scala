package main.scala.me.decentos

import java.io.{FileNotFoundException, FileWriter}

import scala.io._

object HandlerType extends Enumeration {
  val XML, JSON, CSV, TXT, UNKNOWN = Value
}

abstract class Handler {
  val hType: HandlerType.Value
  var next: Handler = _

  def setNext(handler: Handler): Handler = {
    next = handler
    next
  }

  def process(inputFilename: String, outputFilename: String): Unit = {
    if (getFileType(inputFilename) == this.hType)
      processFile(inputFilename, outputFilename)
    else if (next != null)
      next.process(inputFilename, outputFilename)
  }

  def getFileType(filename: String): HandlerType.Value = {
    filename.toLowerCase.replaceAll(" +", "").split("\\.").last match {
      case "xml" => HandlerType.XML
      case "json" => HandlerType.JSON
      case "csv" => HandlerType.CSV
      case "txt" => HandlerType.TXT
      case _ => HandlerType.UNKNOWN
    }
  }

  def processFile(inputFilename: String, outputFilename: String): Unit

  def copyFile(inputFilename: String, outputFilename: String): Unit = {
    val src: BufferedSource = Source.fromFile(inputFilename)
    val fw = new FileWriter(outputFilename, true)
    try {
      src.getLines.foreach(l => fw.write(s"$l\n"))
    } catch {
      case _: FileNotFoundException => Logger.log(s"File $inputFilename doesn't exist")
      case e: Throwable => Logger.log(s"Error processing file $inputFilename: ${e.toString}")
    } finally {
      src.close()
      fw.close()
    }
  }
}
