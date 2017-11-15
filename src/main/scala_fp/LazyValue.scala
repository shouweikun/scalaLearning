import scala.collection.immutable.Stream.Empty

/**
  * Created by john_liu on 2017/9/15.
  */
object LazyValue {
    sealed trait myStream[+A]{
      def exists[A](p:A=>Boolean):Boolean = this match {
        case myCons(h,t) => p(h())||t().exists(p)
        case _=>false
      }
    }
    sealed case object myEmpty extends myStream[Nothing]
    sealed case class  myCons[+A](h:()=>A,t:() => myStream[A])   extends myStream[A] {

    }
    sealed object myStream {
        def mycons[A](hd: => A,tl: => myStream[A]):myStream[A] ={
            lazy val head = hd
            lazy val tail = tl
            myCons(()=>head,()=>tail)
        }

        def empty[A]:myStream[A] = myEmpty

        def apply[A](as :A*):myStream[A] = {
            if (as.isEmpty) myEmpty else myCons(()=>as.head, ()=>apply(as.tail:_*))
        }

    }

}
