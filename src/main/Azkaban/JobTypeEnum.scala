/**
  * Created by john_liu on 2017/8/1.
  */
object JobTypeEnum extends Enumeration{

  val command = Value(1,"command")
  val Java    = Value(2,"java")
  val Javaprocess = Value(3,"javaprocess")
  val HadoopJava =Value(4,"hadoopJava")
  val flow       =Value(5,"flow")

//  def main(args: Array[String]): Unit = {
//    println(command.toString)
//  }
}
