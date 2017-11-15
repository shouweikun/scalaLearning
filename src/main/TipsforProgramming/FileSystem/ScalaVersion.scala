package FileSystem

import java.io.{BufferedWriter, File, FileWriter, IOException}

/**
  * Created by john_liu on 2017/7/17.
  */
object ScalaVersion extends App{

  readFile("/Users/john_liu/Desktop/toupload")
  deleteFile("/Users/john_liu/Desktop/toupload1")

/**未增加异常处理**/

       /**递归遍历文件夹**/
  def readFile (filepath:String) :Unit={
    val file = new File(filepath)
    if(!file.isDirectory){
      println("文件")
      println("path=" + file.getPath)
      println(s"Abspath=${file.getAbsolutePath}")
      println(s"name= ${file.getName}")
    }
    else if (file.isDirectory) {
      println("文件夹")
      println(s"path=${file.getPath}")
      file.list().foreach(readFile(_))
    }
  }

  /**
    * 删除某个文件夹下的所有文件夹和文件
    */
  def deleteFile(filepath: String):Unit = {
    val file = new File(filepath)
    if(file.isFile){
      println(s"${file.getAbsolutePath}:delete${file.delete}")
    }
    else if(file.list.length== 0){

      file.delete()
    }
         else{

      file.list().foreach{
        filename =>deleteFile(filepath+ "/" +filename)

      }
    }
  }

  /** **
    *
    * 写或者追加文件
    *
    * @return BufferedWriter
    *****/
  @throws[IOException]
  def WritetoFile(filepath: String, append: Boolean): BufferedWriter = {
    val file = new File(filepath)
    if (!file.exists) file.createNewFile
    val fileWriter = new FileWriter(file.getName, append)
    val bufferWriter = new BufferedWriter(fileWriter)
    bufferWriter
  }
}
