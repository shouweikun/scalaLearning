import org.apache.spark.SparkContext

/**
  * Created by john_liu on 2017/8/24.
  */
object Accmulator {

  val sc = new SparkContext()
  val file = sc.textFile("")
  val blankLines = sc.accumulator(0)
  val callSigns = file.flatMap{
    lines => if(lines == ""){
      blankLines += 1
    }
      lines.split("")
    }

  }

