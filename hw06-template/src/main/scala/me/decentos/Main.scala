package main.scala.me.decentos

object Main extends App {
  val matrixOps: MatrixOps = args(0) match {
    case "*" => new MatrixMult(args.slice(1, 3), args(3))
    case "+" => new MatrixAdd(args.slice(1, 3), args(3))
    case "t" => new MatrixTrans(args.slice(1, 2), args(2))
    case "d" => new MatrixDet(args.slice(1, 2), args(2))
    case _ => println("Unknown operation")
      sys.exit(-1)
  }
  matrixOps.run()
}
