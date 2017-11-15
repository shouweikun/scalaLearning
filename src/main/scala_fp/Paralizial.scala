/**
  * Created by john_liu on 2017/9/18.
  */
object Paralizial {
  sealed trait myMonoid[A]{
     def op(a1:A,a2:A):A
     def zero:A
   }
  val stringMonid  = new myMonoid[String] {
    def op(a1:String,a2:String) ={
      a1 + a2
    }
    def zero  =""

  }
  stringMonid.op("1","4")

}
