package main.scala.me.decentos

class TXTHandler extends Handler {
  override val hType: HandlerType.Value = HandlerType.TXT

  override def processFile(inputFilename: String, outputFilename: String): Unit = {
    Logger.log(s"Обработчик TXT получил файл $inputFilename")
    copyFile(inputFilename, outputFilename)
  }
}
