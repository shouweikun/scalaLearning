import java.io.{BufferedWriter, File, FileWriter, IOException}

import scala.beans.BeanProperty
/**
  * Created by john_liu on 2017/8/1.
  */
@BeanProperty
class CommandJob extends AzkabanJob{

 //主构造器方法
   this.Type = JobTypeEnum.command.toString

   this.Retry_back ="3000";
   this.Retries    ="3";

  def this(instruction:String) = {
    this()
    this.Instructions =instruction
  }

  override def makefile(path:String) = {
    val writer = super.makefile(path)

    val instructionArray = this.Instructions.split(",")
    for(i<-0 to instructionArray.size-1){
      val command = if(i ==0)"command"else s"command.$i"
      writer.write(s"$command = ${instructionArray(i)}")
      writer.newLine()
    }
    if(NoficationsMail.trim!=""){
      writer.write(NoficationsMail)
    }
    writer.close()
    writer
  }

}
