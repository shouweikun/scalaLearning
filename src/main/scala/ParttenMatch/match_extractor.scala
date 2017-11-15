package ParttenMatch

/**
  * Created by john_liu on 2017/7/12.
  */
object Match_extractor extends App{

  val  arr = Range(1,4).toArray
  println(arr)
  arr match {
    case Array(1,x,_*) => println("dd")
    case Array(1,x)   => println(s"$x")
    case _ :Any       => println("33")
  }

  val (q,r) = BigInt(10) /% 3
  println(s"$q,$r")
}
