package GenerciType

/**
  * Created by john_liu on 2017/8/23.
  */
object GenericFunc {

  def getMiddle[T](a:Array[T]) = a(a.length/2)
  //可以具现化方法
  def getStringArrayMiddle = getMiddle[String](_)

}
