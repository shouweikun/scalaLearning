package GenerciType

/**
  * Created by john_liu on 2017/8/23.
  */
object Downerbound {

  class Pair[T](val first:T,val second:T){
    def replaceFirst[R >: T](newFirst:R) =new Pair(newFirst,second)
  }
}
