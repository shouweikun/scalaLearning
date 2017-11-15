package scala_collection

/**
  * Created by john_liu on 2017/7/11.
  */
object StreamDemo extends App{


  val tenOrMore = numsFrom(10)
  print(tenOrMore)
    def numsFrom(n:BigInt) :Stream[BigInt] = n #:: numsFrom(n+2)
  print(tenOrMore.take(2).force)
  print(tenOrMore.tail)
  print(tenOrMore.tail)

}
