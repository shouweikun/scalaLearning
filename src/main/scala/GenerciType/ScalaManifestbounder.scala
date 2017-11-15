package GenerciType

/**
  * Created by john_liu on 2017/8/23.
  */
object ScalaManifestbounder{
 //     实例化一个泛型数组

  def makePair[ T:Manifest](first:T,second :T): Unit ={

    val r = new Array[T](2);
    r(0) =first;
    r(1) = second
    return r

  }


}
