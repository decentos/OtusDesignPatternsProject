package main.scala.me.decentos

class CSVHandler extends Handler {
  override val hType: HandlerType.Value = HandlerType.CSV

  override def processFile(inputFilename: String, outputFilename: String): Unit = {
    Logger.log(s"Обработчик CSV получил файл $inputFilename")
    copyFile(inputFilename, outputFilename)
  }
}
