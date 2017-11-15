package scala_collection

import scala.collection.mutable

/**
  * Created by john_liu on 2017/7/12.
  */
object ThreadSafe_Concurrent extends App{
/**通过继承对应的trait来达到同步操作的需求*/
  /**还可以将java.utils.concurrent包中的集合转化成scala集合来使用*/
  val scores = new mutable.HashMap[String,Int] with scala.collection.mutable.SynchronizedMap[String,Int]
  /**scala 给出了很好的集合并发方案**/
  val coll   =  Set(0,1,2)
  val collection =coll + 3
  println(s"$coll,${coll.getClass}")
  println(coll.par.sum)
  println(collection.par.sum)
  /**不要试图去更新一个共享变量，会导致结果不可预知*/
  /**并不是所用的方法都是可并行化的，要求操作符可以自由结合*/
}
