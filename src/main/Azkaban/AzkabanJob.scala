import java.io.{BufferedWriter, File, FileWriter, IOException}

import scala.beans.BeanProperty

/**
  * Created by john_liu on 2017/7/31.
  */
@BeanProperty
class AzkabanJob {
  var Job_Name     =""
  var Type: String = ""
  var Instructions =""
  var Retries      = ""
  var Retry_back   = ""
  var NoficationsMail  = ""
  var FailureMails     = ""
  var SuccessMails     = ""
  var dependencies     = ""
  var finalpath             = ""

  def makefile(path:String):BufferedWriter = {
      this.finalpath = s"$path.job"
     val writer = WritetoFile(finalpath,true)
     val jobname = s"#$Job_Name"
     val jobtype = s"type = $Type"
     writer.write(jobname)
     writer.newLine()
     writer.write(jobtype);
     writer.newLine()
     if(this.FailureMails != "")
       {
         val failure_emails = s"failure.emails = ${this.FailureMails}"
         writer.write(failure_emails)
         writer.newLine()
       }
     if (this.SuccessMails !="")
       {
         val success_emails = s"success.emails = ${this.SuccessMails}"
         writer.write(success_emails)
         writer.newLine()
       }
    if (this.NoficationsMail !="")
      {
        val notify_emails = s"notify.emails = ${NoficationsMail}"
        writer.write(notify_emails)
        writer.newLine()
      }
     if(this.Retries!="") {
       val retries = s"retries = ${Retries}"
       writer.write(retries);
       writer.newLine()
     }
     if(this.Retry_back !="")
       {
        val retry_back= s"retry.back = ${Retry_back}"
         writer.write(retry_back);
         writer.newLine()
       }


    if(this.dependencies.trim != "") {
      val dependencies = s"dependencies = ${this.dependencies}"
      writer.write(dependencies)
      writer.newLine()
    }
     writer.flush()
     writer
  }
  @throws[IOException]
  def WritetoFile(filepath: String, append: Boolean): BufferedWriter = {
    val file = new File(filepath)
    if (!file.exists) file.createNewFile
    val fileWriter = new FileWriter(file, append)
    val bufferWriter = new BufferedWriter(fileWriter)
    bufferWriter
  }
}
