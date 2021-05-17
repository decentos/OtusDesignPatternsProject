package main.scala.me.decentos

import java.io.FileWriter
import scala.math._

// Element of the Fibonacci sequence
class Fibonacci {
  private val phi: Double = (1.0 + sqrt(5.0)) / 2.0
  private var index: Int = _

  def this(n: Int) = {
    this()
    if (n < 0) throw new IllegalArgumentException(s"Fibonacci index should be positive, not $n")
    this.index = n
  }

  def getIndex: Int = index

  def writeValue(filename: String): Unit = {
    val fw = new FileWriter(filename, true)
    try {
      fw.write(s"$value\n")
    }
    finally fw.close()
  }

  def value: Long = {
    round((pow(phi, index) - pow(-phi, -index)) / (2.0 * phi - 1.0))
  }
}

object Fibonacci {
  def apply(n: Int): Fibonacci = new Fibonacci(n)
}
