package ShellScript
import  sys.process._

/**
  * Created by john_liu on 2017/8/2.
  */
object Process extends App{

      val instruction1 = "ls" !!;
      val instruction2 = "cd ~" !
      val instruction3 = "ps -ax" #| "grep Application"!!

  println(instruction1,instruction2,instruction3)


}
