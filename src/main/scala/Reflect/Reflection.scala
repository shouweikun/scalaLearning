package Reflect

/**
  * Created by john_liu on 2017/8/8.
  */

object Reflection extends App{
  val l  = List(1,2,3)
  import scala.reflect.runtime.{universe => ru}
  def getTypeTag[T:ru.TypeTag](Obj:T) =ru.typeTag[T]
  def getClassTag(Obj:Any) =ru.getClass
  val thetype   = getTypeTag(l).tpe

  //println(thetype)
  val decls =thetype.decls
  //println(decls)

  case class Person(name:String)


  /**首先生成mirror，单独的typeSymbol 是没有意义的，只能查看声明**/
  val m1 = ru.runtimeMirror(getClass.getClassLoader)
  val classperson = ru.typeOf[Person].typeSymbol.asClass
  val kankan = ru.typeOf[Person]


/****mirror和classSymbol绑定***/
  val cm = m1.reflectClass(classperson)
  /*******拿到构造器的Symbol***********/
  val ctor = ru.typeOf[Person].decl(ru.nme.CONSTRUCTOR).asMethod
  /********利用mirror 绑定出可用的构造器**********/
  val ctorm = cm.reflectConstructor(ctor)
  /********利用构造器初始化对象**********/
  val p     = ctorm("ddd")
  println(p.asInstanceOf[Person].name)



  case class Purchase(var name:String,orderNumber:Int,var shipped:Boolean)
  {
    def ddd ={
      println(s"$name")
    }
  }
  val purchase = Purchase("dddd",1,false)
  val m2       = ru.runtimeMirror(purchase.getClass.getClassLoader)
  val purchasetype = getTypeTag(purchase).tpe


  //val kk           = ru.typeOf[Purchase]
  println(purchasetype.decls)
  val shippingTermSymb = purchasetype.decl(ru.TermName("shipped")).asTerm
  val nameSymb = purchasetype.decl(ru.TermName("name")).asTerm
  val ordernameSymb = purchasetype.decl(ru.TermName("orderNumber")).asTerm
  val dddmethodSymb = purchasetype.decl(ru.TermName("ddd")).asMethod
  println("dfdfd"+shippingTermSymb.name)

  val im = m2.reflect(purchase)

  //println(shippingFieldMirror.get)
  im.reflectField(shippingTermSymb).set(true)
  im.reflectField(nameSymb).set("dddd")
  im.reflectField(ordernameSymb).set(343)
  val a = im.reflectMethod(dddmethodSymb)

 // println(shippingFieldMirror.get)

  print(getClassTag(purchase).getDeclaredFields)
}
