/**
  * Created by john_liu on 2017/9/1.
  */
class cafe {
   def buyCaffe(cc:CreditCard) ={
     val cup = Coffee()
     (cup,Charge(cc,cup.price))
   }

   def buyCoffees(cc:CreditCard,n:Int):(List[Coffee],Charge) = {
     val purchases: (List[(Coffee, Charge)]) = {
       List.fill(n)(buyCaffe(cc))
     }
      val result = purchases.unzip
     (result._1,result._2.reduce{
       (charge1,charge2)=>charge1.combine(charge2)
     })


   }





 sealed case class Charge(cc:CreditCard,amount:Double){
   def combine (other:Charge) = {
     if(cc == other.cc) {
       Charge(cc, other.amount)
     }else{
       throw new Exception("fuck,different card")
     }
   }
 }
 sealed case class Coffee(price:Double =11.11) {
//   def price = {
//     11.11
//   }
 }
 sealed case class CreditCard()
}
