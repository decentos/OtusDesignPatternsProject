package main.scala.me.decentos

class JSONHandler extends Handler {
  override val hType: HandlerType.Value = HandlerType.JSON

  override def processFile(inputFilename: String, outputFilename: String): Unit = {
    Logger.log(s"Обработчик JSON получил файл $inputFilename")
    copyFile(inputFilename, outputFilename)
  }
}
