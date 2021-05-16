package main.scala.me.decentos

// Abstract Iterator
trait Iterator[T] {
  var current: T

  def hasNext: Boolean

  def next(): T
}

// Iterator for Fibonacci sequence with the movement in the forward direction
class FibonacciForwardIterator(from: Fibonacci, to: Fibonacci) extends Iterator[Fibonacci] {
  override var current: Fibonacci = from

  override def hasNext: Boolean = {
    if (current.getIndex < to.getIndex) true
    else false
  }

  override def next(): Fibonacci = {
    current = Fibonacci(current.getIndex + 1)
    current
  }
}

// Iterator for Fibonacci sequence with the movement in the backward direction
class FibonacciBackwardIterator(from: Fibonacci, to: Fibonacci) extends Iterator[Fibonacci] {
  override var current: Fibonacci = from

  override def hasNext: Boolean = {
    if (current.getIndex > to.getIndex) true
    else false
  }

  override def next(): Fibonacci = {
    current = Fibonacci(current.getIndex - 1)
    current
  }
}
