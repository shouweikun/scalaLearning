package scala_collection

/**
  * Created by john_liu on 2017/7/12.
  */
object Mutable_ImmtubleDemo extends App {

  def digits(n:Int):Set[Int] = {
    if (n < 0) digits(-n)
    else if (n < 10) Set(n)
    else digits(n / 10 + n % 10)
  }

    print(digits(1) ++ digits(2) ++ digits(1))

}
