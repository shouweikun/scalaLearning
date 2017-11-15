package scala_collection

import scala.collection.mutable.ArrayBuffer

/**
  * Created by john_liu on 2017/7/13.
  */
object SetDemo extends App {

   /*****bit set*****/
  val digits = Set(1,2,5,6)
  val numbers = ArrayBuffer(1,2,3)
  numbers += 5
  println(numbers)
/******使用 var += 或 :+= ******/
  var numberVector = Vector(1,2,3)
  numberVector :+= 5

  val list1  = List(1)
  val list2  = List(2)
  println(list1 ++ list2)
}
