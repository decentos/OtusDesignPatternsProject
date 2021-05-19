package main.scala.me.decentos

class XMLHandler extends Handler {
  override val hType: HandlerType.Value = HandlerType.XML

  override def processFile(inputFilename: String, outputFilename: String): Unit = {
    Logger.log(s"Обработчик XML получил файл $inputFilename")
    copyFile(inputFilename, outputFilename)
  }
}