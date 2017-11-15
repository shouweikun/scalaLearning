package ParttenMatch

/**
  * Created by john_liu on 2017/7/12.
  */
object MatchArr extends App {

  val arr  = Range(1,10).toArray
  arr match {
    case Array(0,y) => println("impossible")
    case Array(1,_*)=>println("right")
  }

  val list = arr.toList
  list match {
    case 1 :: tail =>println(s"$tail")

  }

  val pair = (0,1)
  pair match {
    case (0,x) => println(s"it contains 0,$x")
  }

  
}
