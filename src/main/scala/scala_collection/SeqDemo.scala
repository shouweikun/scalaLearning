package scala_collection

/**
  * Created by john_liu on 2017/7/13.
  */
object ListDemo extends App{

   val digits = List(4,2)
  println(digits.tail,digits.tail.head)
   val digits2 = 9 :: digits
  println(digits2)
  /** ::操作符是右结合的*/

  val lst = scala.collection.mutable.LinkedList(1,-2,7)
  var cur = lst
  while(cur != Nil) {
    if (cur.elem < 0) {
      cur.elem = 0
    }
    cur = cur.next
  }
}
