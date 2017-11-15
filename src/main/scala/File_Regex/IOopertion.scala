package File_Regex

import java.io.{File, FileInputStream, PrintWriter}

import scala.io.Source

/**
  * Created by john_liu on 2017/7/26.
  */
object IOopertion extends App{

 /******************************************************/
  def ReadingFile = {
    val path = "/Users/john_liu/Finupprojects/scala_learning/src/main/resources/"
    val filename = "test.txt"

    val source = Source.fromFile(s"$path$filename")
    //读取行
    val lineIterator = source.getLines()
    val linelist = lineIterator.toList
    println(s"${lineIterator.mkString(",")}")
    println(s"${linelist.mkString("*")}")
    //读取字符
    /** 读取单个字符，直接把Source对象当做迭代器 */
    for (c <- source) {
      println(s"$c")
    }
    /** 想查看不处理 */
    val iter = source.buffered
    while (iter.hasNext) {
      ///????
    }
    /** *读取词法单元和数字 **/
      /***一个快而脏的方式，读取源文件中所有以空格隔开的词法单元**/
    val tokens = source.mkString.split("\\S+")
    val numbers =tokens.map(_.toDouble)

    /** 写入文本文件**/

    val out = new PrintWriter(s"$path/$filename")
    for(i<-1 to 100)out.println(i)
    out.close()

    /** 从URL或其他源读取
      */
    val source1 = Source.fromURL("url","charset")
    val source2 = Source.fromString("String")
    //读取标准输出
    val source3 = Source.stdin
    //读取二进制文件
    //scala 并未给二进制文件提供专用方法
    val file = new File(filename)
    val in   = new FileInputStream(file)
    val bytes= new Array[Byte](file.length.toInt)
    in.read(bytes)
    in.close()


  }

}
