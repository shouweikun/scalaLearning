package ShellScript
import java.io.{BufferedReader, InputStreamReader}

import com.typesafe.config.{Config, ConfigFactory}
/**
  * Created by john_liu on 2017/7/14.
  */
object ScalaVersion extends App{

  //  val configration = ConfigFactory.load("/Users/john_liu/Finupprojects/scala_learning/src/main/resources/shell_reading_app.conf")
  //  val Command      = configration.getString("shell.names").split(",")
  val Command ="/Users/john_liu/Finupprojects/scala_learning/src/main/resources/ShellScripts/test.sh".split(",")
  Command.par.map(path=>executeShellScript(path)(true)(false))

  def executeShellScript(path:String)(printInput:Boolean)(printErr:Boolean) ={
     val process   = Runtime.getRuntime.exec(path)
     val exitvalue = process.waitFor()
     val input     = new BufferedReader(new InputStreamReader(process.getInputStream))
     val err       = new BufferedReader(new InputStreamReader(process.getErrorStream))
     //控制输出的格式
    (printInput,printErr) match {
      case (true,true)  => {
        printInfo(input)
        printInfo(err)
      }
      case (true,false) => {
        printInfo(input)
      }
      case (false,true) => {
        printInfo(err)
      }
      case (false,false)=> {
        //do nothing
      }
      case (_,_)        => {
        throw new Exception
      }
    }

     exitvalue


  }
  def printInfo(inputStream:BufferedReader) ={
    var info = inputStream.readLine()
    while( info!= null){
      println(info)
      info = inputStream.readLine()
    }
  }
}
