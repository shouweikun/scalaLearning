import java.io.BufferedWriter
/**
  * Created by john_liu on 2017/8/14.
  */
class FlowJob extends AzkabanJob {
  this.Type = JobTypeEnum.flow.toString
   var Flow_name = ""
  override def makefile(path: String): BufferedWriter = {
    val writer = super.makefile(path)
    val flow_name =s"flow.name = ${this.Flow_name}"
    writer.write(flow_name)
    writer.newLine()
    writer.flush()
    writer.close()
    writer

  }
}