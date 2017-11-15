package File_Regex

/**
  * Created by john_liu on 2017/8/4.
  */
object Regex extends App{
   //构造正则表达式
  val list = List("1","2","3","4").mkString(" ")
  val numPattern = "[0-9]+".r
  val wsnumwsPattern = """\s+[0-9]+\s+""".r
  for (matchString <- numPattern.findAllIn(list)){
    println(matchString)
    //寻找全的
    numPattern.findAllIn(list)
    //首个匹配项
    numPattern.findFirstIn(list)
    //检查某个字符串的开始部分能否匹配
    numPattern.findPrefixOf(list)
    //替换首个匹配项或全部匹配项
    numPattern.findFirstIn(list)
    numPattern.replaceAllIn(list,"kk")
     /*******正则表达式组*******/
    val numitemPattern = "([0-9])([a-z]+)".r
    val numitemPattern(num,item) = list
    //可以用在模式匹配中
    println(num,item)
    //多个匹配项提取分组内容，可以这样使用for语句
    for(numitemPattern(num,item)<-numitemPattern.findAllIn(list))
      {
        println(num,item)
      }
  }
}
