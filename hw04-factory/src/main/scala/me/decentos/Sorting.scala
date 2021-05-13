package main.scala.me.decentos

import java.io.FileWriter

trait Sorting {
  def sorted(list: List[Int])(implicit ord: Ordering[Int]): List[Int]
}

trait Writer {
  def writeType(sortType: String, outputFilename: String): Unit = {
    if (outputFilename != null) {
      val fw = new FileWriter(outputFilename, false)
      try {
        fw.write(s"$sortType\n")
      }
      finally fw.close()
    }
  }
}

// Сортировка по умолчанию
class DefaultSort(outputFilename: String) extends Sorting with Writer {
  def sorted(list: List[Int])(implicit ord: Ordering[Int]): List[Int] = {
    writeType("Default Sort", outputFilename)
    list.sorted
  }
}

// Сортировка выбором
class SelectionSort(outputFilename: String) extends Sorting with Writer {
  override def sorted(list: List[Int])(implicit ord: Ordering[Int]): List[Int] = {
    writeType("Selection Sort", outputFilename)

    def remove(e: Int, list: List[Int]): List[Int] =
      list match {
        case Nil => Nil
        case x :: xs if x == e => xs
        case x :: xs => x :: remove(e, xs)
      }

    list match {
      case Nil => Nil
      case _ =>
        val min = list.min
        min :: sorted(remove(min, list))
    }
  }
}

// Сортировка вставкой
class InsertionSort(outputFilename: String) extends Sorting with Writer {
  override def sorted(list: List[Int])(implicit ord: Ordering[Int]): List[Int] = {
    writeType("Insertion Sort", outputFilename)

    def insert(list: List[Int], value: Int): List[Int] = list.span(x => ord.lt(x, value)) match {
      case (lower, upper) => lower ::: value :: upper
    }

    list.foldLeft(List.empty[Int])(insert)
  }
}

// Сортировка слиянием
class MergeSort(outputFilename: String) extends Sorting with Writer {
  override def sorted(input: List[Int])(implicit ord: Ordering[Int]): List[Int] = {
    // Записываем метку в файл
    writeType("Merge Sort", outputFilename)

    def merge(left: List[Int], right: List[Int]): LazyList[Int] = (left, right) match {
      case (x :: xs, y :: _) if x <= y => x #:: merge(xs, right)
      case (_ :: _, y :: ys) => y #:: merge(left, ys)
      case _ => if (left.isEmpty) right.to(LazyList) else left.to(LazyList)
    }

    def sort(input: List[Int], length: Int): List[Int] = input match {
      case Nil | List(_) => input
      case _ =>
        val middle = length / 2
        val (left, right) = input splitAt middle
        merge(sort(left, middle), sort(right, middle + length % 2)).toList
    }

    sort(input, input.length)
  }
}