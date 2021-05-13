package test.scala.me.decentos

import main.scala.me.decentos.{DefaultSort, InsertionSort, MergeSort, SelectionSort}
import org.scalatest.funsuite.AnyFunSuite

class SortingTest extends AnyFunSuite {
  test("Default test") {
    val inputList: List[Int] = List(5, 1, 3)
    val etalon: List[Int] = List(1, 3, 5)
    val sortedList = new DefaultSort(null).sorted(inputList)

    assert(sortedList === etalon)
  }

  test("Selection test") {
    val inputList: List[Int] = List(5, 1, 3)
    val etalon: List[Int] = List(1, 3, 5)
    val sortedList = new SelectionSort(null).sorted(inputList)

    assert(sortedList === etalon)
  }

  test("Insertion test") {
    val inputList: List[Int] = List(5, 1, 3)
    val etalon: List[Int] = List(1, 3, 5)
    val sortedList = new InsertionSort(null).sorted(inputList)

    assert(sortedList === etalon)
  }

  test("Merge test") {
    val inputList: List[Int] = List(5, 1, 3)
    val etalon: List[Int] = List(1, 3, 5)
    val sortedList = new MergeSort(null).sorted(inputList)

    assert(sortedList === etalon)
  }
}
