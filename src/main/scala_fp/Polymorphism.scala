/**
  * Created by john_liu on 2017/9/1.
  */
object Polymorphism {

  def findFirst[A](as:Array[A],p :A => Boolean):Int ={
    def loop(n:Int):Int  ={
      if (n>=as.length) -1
      else if(p(as(n))) n
      else loop(n+1)

    }
    loop(0)
  }

  def findAll[A](as:Array[A],p:A=>Boolean):List[Int] ={

    def loop(n:Int,list: List[Int]=List.empty): List[Int] ={
      if (n>=as.length) list
      else if(p(as(n))) loop(n+1,(list.:+(n)))
      else loop(n+1,list)
    }

    loop(0)
  }

  def isSorted[A](as:Array[A],ordered: (A,A) => Boolean):Boolean ={
    def loop(n:Int):Boolean ={
      if(n>=as.length) true
      else if(ordered(as(n),as(n+1))) loop(n+1)
      else false
   }
    loop(0)
  }
}
