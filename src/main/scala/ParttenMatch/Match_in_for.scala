package ParttenMatch

/**
  * Created by john_liu on 2017/7/12.
  */
object Match_in_for extends App {
  /**在for推导式中，失败的例子会被直接忽略*/
  val map = Map(1->1,2->2)
  for((k,v)<-map){println(k,v)}
}
