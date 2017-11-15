package ParttenMatch



/**
  * Created by john_liu on 2017/7/12.
  */
object Match_CaseClass extends App{

  abstract class amount
  case class Dollar(value:Double) extends amount
  case class Currency(value:Double,unit:String) extends amount

  val amount  = Dollar(11)
  matchfunc(amount)

  def matchfunc(amt :amount) = {amt match {
    case Dollar(v) => println(v)

  }

  }
}
