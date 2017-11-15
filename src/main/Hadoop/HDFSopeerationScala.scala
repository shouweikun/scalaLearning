import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}

/**
  * Created by john_liu on 2017/7/26.
  */
object HDFSopeerationScala {
  def initFileSystem :FileSystem= {
    val conf = new Configuration
    val fs = FileSystem.get(conf)
    fs
  }
  def rename (src:String,dst: String) :Boolean ={

    val fs =initFileSystem
    val flag = fs.rename(new Path(src), new Path(dst))
    flag
  }

  def getDirectoryFromHdfs(dst:String) ={
    val fs = initFileSystem
    val filelist = fs.listStatus(new Path(dst))
    filelist
  }
  def mkdir(dir: String) ={
    val fs = initFileSystem
    val flag = fs.mkdirs(new Path(dir))
    flag
  }
  def delete(dir: String) = {
    val fs = initFileSystem
    val flag = fs.delete(new Path(dir))
    flag
  }
  def putfiles(src:String,dst:String) ={
    val fs = initFileSystem
    fs.copyFromLocalFile(new Path(src),new Path(dst))
  }
}
