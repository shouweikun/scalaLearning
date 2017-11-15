import scala.annotation.tailrec

/**
  * Created by john_liu on 2017/9/1.
  */
object DataStructure extends App{
  sealed trait mylist[+A]
  case object myNil extends mylist[Nothing]
  case class myCons[+A](head :A,tail :mylist[A]) extends mylist[A]

  object mylist {
    def size[A](list:mylist[A]):Int={
      def loop(list:mylist[A],acc:Int):Int= {
        list match {
          case myNil => acc
          case myCons(x, xs) =>loop(xs,acc+1)
        }
      }
        loop(list,0)
    }
    def sum(ints: mylist[Int]): Int = ints match {
      case myNil => 0
      case myCons(x, xs) => x + sum(xs)
    }

    def product(ds: mylist[Double]): Double = ds match {
      case myNil => 1.0
      case myCons(0.0, _) => 0.0
      case myCons(x, xs) => x * product(xs)
    }

    def apply[A](as: A*): mylist[A] = {
      if (as.isEmpty) myNil
      else myCons(as.head, apply(as.tail: _*))
    }

    //homework
    def mytail[A](list: mylist[A]) = list match {
      case myNil => throw new Exception
      case myCons(x, xs) => xs
    }

    def mydrop[A](list: mylist[A], n: Int):mylist[A] = {
      if(n<0) throw new Exception("数字不合法")
      (list, n) match {
        //case (_, int) if (int < 0) => throw new Exception("数字不合法")
        case (_, 0) => list
        case (myCons(x,xs),n)=>mydrop(xs,n-1)
        case (myNil,_)=>myNil
        //      case ()
      }
    }
    @tailrec
    def mydropwhile[A](l:mylist[A])(f:A=>Boolean) :mylist[A] = {
      l match {
        case myNil => l
        case myCons(x,sx)=>{
          if(f(x)) mydropwhile(sx)(f)
          else l
        }

      }
    }

   def mydropwhile2[A](l:mylist[A])(f:A=>Boolean):mylist[A] ={
     def loop(list :mylist[A]):mylist[A] ={
       if(list.isInstanceOf[myCons[A]]){
         if(f(list.asInstanceOf[myCons[A]].head)){
           loop(list.asInstanceOf[myCons[A]].tail)
         }else list.asInstanceOf[myCons[A]]
       }else{myNil}
     }
     loop(l)
   }

  def myappend[A](list1:mylist[A],list2:mylist[A]):mylist[A]={
    list1 match {
      case myNil => list2
      case myCons(x,xs)=>myCons(x,myappend(xs,list2))

    }
   }


  def myinit[A](list:mylist[A]):mylist[A]={
    def loop (intro:mylist[A],outro:mylist[A]):mylist[A] ={
      intro match {

        case myCons(x,xs)=>{
          if(xs==myNil)
            {
              outro
            }else{
            loop(xs,myCons(x,outro))
          }
        }
        case myNil => throw new Exception
      }
    }
    loop(list,myNil)
  }

  def myfoldRight[A,B](list:mylist[A],z:B)(f:(A,B)=>B):B ={
    list match {
      case myNil => z
      case myCons(x,xs) =>f(x,myfoldRight(xs,z)(f))
    }
  }

  def myfoldLeft[A,B](list:mylist[A],z:B)(f:(A,B)=>B):B ={
    list match {
      case myNil  => z
      case myCons(x,xs) =>myfoldLeft(xs,f(x,z))(f)
    }
  }

  def mysum2(list:mylist[Int]) ={
    myfoldLeft(list,0)((x:Int,y:Int)=>x+y)
  }

  def myproduct2(list:mylist[Double]) ={
    myfoldLeft(list,1.0)((x:Double,y:Double)=>x*y)
  }
  def myreverse[A](list:mylist[A]) ={
    def stash[A](enum:A,list:mylist[A]) = {myCons(enum,list)}
    myfoldLeft(list,myNil.asInstanceOf[mylist[A]])(stash)

  }
  def myAppend2[A](list1:mylist[A],list2:mylist[A])={

     myfoldRight(list1,list2)((x:A,y:mylist[A])=>myCons(x,y))
  }
  def alladd1(list1:mylist[Int]) ={
    myfoldLeft(list1,myNil.asInstanceOf[mylist[Int]])((x:Int,y:mylist[Int])=>myCons(x,y))
  }
//  def mymap[A,B](list:mylist[A])(f:A=>B):mylist[B] ={
//     def func[A,B](elem:A,list:mylist[B])={
//       myCons(f(elem),list)
//     }
//    myfoldLeft(list,myNil.asInstanceOf[mylist[B]])(func)
//  }
//  def myfilter[A](list:mylist[A])(f :A=>Boolean):mylist[A]= {
//
//    }
//  def myreduceRight[A,B](list:mylist[A])(f:(A,A)=>B):B ={
//    ???
////  }
//  def myreduceLeft[B >: A](list:mylist[A])(f:(A,B)=>B):B ={
//
//     if(mylist.size(list)<2)
//       {
//         throw new Exception("数据量小于2")
//       }
//    mylist.myfoldLeft()
//  }
}
  sealed trait myTree[+A]
  sealed case class myLeaf[A](value:A) extends myTree[A]
  sealed case class myBranch[A](left:myTree[A],right:myTree[A])
  object myTree{
    def size[A](tree:myTree[A]):Int={
      tree match {
        case myLeaf =>1
        case myBranch(l:myTree[A],r:myTree[A])=>size(l)+size(r)
      }
    }
    def maximum(tree:myTree[Int]):Int={
      tree match {
        case leaf:myLeaf[Int] => leaf.value
        case myBranch(l:myTree[Int],r:myTree[Int])    =>maximum(l) max maximum(r)
    }

    }

    def depth[A](tree:myTree[A]):Int ={
      tree match {
        case leaf:myLeaf[A] => 1
        case myBranch(l:myTree[A],r:myTree[A]) =>depth(l).max (depth(r))}
      }
    def haoyongmySON[A,B](mysonYong:myTree[A])(f:A=>B):myTree[B]={
      mysonYong match {
        case xiaoSon :myLeaf[A] =>myLeaf(f(xiaoSon.value))
        case myBranch(lSon:myTree[A],rSon:myTree[A])=>myBranch(haoyongmySON(lSon)(f),haoyongmySON(rSon)(f)).asInstanceOf[myTree[B]]
      }
    }
    }
   def fold[A,B](tree:myTree[A])(f:A=>B)(g:(B,B)=>B) :B={
     tree match {
       case leaf :myLeaf[A] => f(leaf.value)
       case myBranch(l:myTree[A],r:myTree[A])   =>g(fold(l)(f)(g),fold(r)(f)(g))
     }

   def apply[A](elems:A*)  ={

     ???


   }
   }

  }

