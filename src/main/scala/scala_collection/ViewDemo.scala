package scala_collection
import math.pow
/**
  * Created by john_liu on 2017/7/12.
  */
object ViewDemo extends App{

  val powers  = (0 until 1000).view.map(pow(3,_))
  print(powers(100))
  val powers2 = (0 to 1000).view.map(pow(3,_)).map(1 / _).force
  print(powers2)

  /*********懒视图，被使用时才会触发计算*********/
}
