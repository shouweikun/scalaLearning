/**
  * Created by john_liu on 2017/9/15.
  */
object pure_fp {
   sealed object pure_func_random_generator{
     trait RNG
     {
       def nextInt:(Int,RNG)
       //def unit[A](a:A):Rand[A] =
       def map[A,B](S:Rand[A])(f:A=>B):Rand[B] ={
         rng =>
           val (a,rng1) =S(rng)
           (f(a),rng1)

       }
       def map2[A,B,C](ra:Rand[A],rb:Rand[B])(f:(A,B) =>C):Rand[C]=
       {
         rng => val (a,rng1) =ra(rng)
                val (b,rng2) =rb(rng)
           (f(a,b),rng)
     }
     }
   sealed type Rand[+A] = RNG => (A,RNG)

     sealed case class SimpleRNG(seed:Long) extends RNG {
       override def nextInt: (Int, RNG) = {
         val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFL
         val nextRNG = SimpleRNG(newSeed)
         val n       = (newSeed >>>16).toInt
         (n,nextRNG)
       }


     }
   }
}
