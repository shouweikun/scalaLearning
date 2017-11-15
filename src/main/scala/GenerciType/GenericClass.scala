package GenerciType

/**
  * Created by john_liu on 2017/8/23.
  */
object GenericClass {
    class Pair[T,S](val first:T,val second :S)
    val p = new Pair(43,"String")

}
