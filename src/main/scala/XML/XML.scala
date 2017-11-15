package XML

import scala.xml._

/**
  * Created by john_liu on 2017/8/23.
  */
object XML extends App {
  //定义XML 字面量
  val doc = <html>
    <head>
      <title>neighborhood</title>
    </head>
  </html>
  //字面量可以使一系列节点
  val items = <li>John</li> <li>liu</li>
  XMLnode
  elemAttributes
  embededExpression
  patternMatch

  def XMLnode = {
    //Elem 类描述的是XML元素
    val elem = <a href="http://scala-lang.org">the
      <em>scala</em>
      language
      <em>fuck</em>
    </a>
    for (n <- elem.child) {
      println(n)
    }
    elem
  }

  def buildbyNodeBuffer = {
    val items = new NodeBuffer
    items += <li>john</li>
    items += <li>liu</li>
    val nodes: NodeSeq = items
  }

  def elemAttributes = {
    val elem = XMLnode
    val url = elem.attributes("href")
    println(url)

    val image = <img alt="neighborhood" src="anshan"/>
    val alt = image.attributes("alt")
    println(alt)

    //不喜欢处理null,可以使用get方法
    val alt2 = elem.attributes.get("href").getOrElse(Text(""))

    //遍历所有属性
    for (attr <- elem.attributes) {
      println(attr.key)
      println(attr.value)
    }

    //也可以调用asAttrMap
    val map = image.attributes.asAttrMap
  }

  def embededExpression = {
    //可以在XML 字面量中包含Scala代码块，动态计算出元素内容
    val items = List("11", "22")
    val xml = <ul>
      <li>
        {items(1)}
      </li>
    </ul>
    println(xml)
    //要在xml中包含花括号，连续写两个即可
    //在xml中嵌入表达式的值，而该字符串成为属性值
      <img src="certainfunc"/>
  }

  def specialNode = {
    val js = <script>
      <![CDATA[if (temp < 0) alert("Cold!")]]>
    </script>
    val code = """if(temp < 0) alert("Cold")"""
    val js1 = <script>
      {PCData(code)}
    </script>

    val g1 = <xml:group>
      <li>111</li> <li>222</li>
    </xml:group>
    val g2 = Group(Seq(<li>sss</li>, <li>dfdf</li>))

    val ol = for (n <- <ol>
      {g1}
    </ol>) yield n
  }

  def patternMatch = {
    val node = <a href="http://scala-lang.org">the
      <em>scala</em>
      language
      <em>fuck</em>
    </a>


  }
}