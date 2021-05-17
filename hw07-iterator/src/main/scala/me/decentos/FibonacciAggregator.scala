package main.scala.me.decentos

trait FibonacciAggregator {
  val iterator: Iterator[Fibonacci]
}

class FibonacciForwardAggregator(from: Fibonacci, to: Fibonacci) extends FibonacciAggregator {
  override val iterator: Iterator[Fibonacci] = new FibonacciForwardIterator(from, to)
}

class FibonacciBackwardAggregator(from: Fibonacci, to: Fibonacci) extends FibonacciAggregator {
  override val iterator: Iterator[Fibonacci] = new FibonacciBackwardIterator(from, to)
}
