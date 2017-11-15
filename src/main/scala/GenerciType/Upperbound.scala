package GenerciType

/**
  * Created by john_liu on 2017/8/23.
  */
object Upperbound {
    class Pair[T<%Comparable[T]](val first:T,val second:T)
  {
    def smaller = if(first.compareTo(second)<0)first else second

  }

}
