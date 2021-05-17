package test.scala.me.decentos

import main.scala.me.decentos.{Fibonacci, FibonacciBackwardAggregator, FibonacciForwardAggregator}
import org.scalatest.funsuite.AnyFunSuite

import scala.collection.mutable.ListBuffer

class FibonacciTest extends AnyFunSuite {
  test("Test 0") {
    val f: Fibonacci = new Fibonacci(0)
    val result = f.value

    assert(result === 0)
  }

  test("Test 1") {
    val f: Fibonacci = new Fibonacci(1)
    val result = f.value

    assert(result === 1)
  }

  test("Test 2") {
    val f: Fibonacci = new Fibonacci(2)
    val result = f.value

    assert(result === 1)
  }

  test("Test 3") {
    val f: Fibonacci = new Fibonacci(3)
    val result = f.value

    assert(result === 2)
  }

  test("Test 4") {
    val f: Fibonacci = new Fibonacci(4)
    val result = f.value

    assert(result === 3)
  }

  test("Test 5") {
    val f: Fibonacci = new Fibonacci(5)
    val result = f.value

    assert(result === 5)
  }

  test("Test from 5 to 10") {
    val f: FibonacciForwardAggregator = new FibonacciForwardAggregator(Fibonacci(5), Fibonacci(10))
    val result: ListBuffer[Long] = ListBuffer()

    while(f.iterator.hasNext) {
      result += f.iterator.current.value
      f.iterator.next()
    }

    assert(result === ListBuffer(5, 8, 13, 21, 34))
  }

  test("Test from 10 to 5") {
    val f: FibonacciBackwardAggregator = new FibonacciBackwardAggregator(Fibonacci(10), Fibonacci(5))
    val result: ListBuffer[Long] = ListBuffer()

    while(f.iterator.hasNext) {
      result += f.iterator.current.value
      f.iterator.next()
    }

    assert(result === Vector(55, 34, 21, 13, 8))
  }
}
