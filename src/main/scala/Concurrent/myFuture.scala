package Concurrent

import scala.concurrent.Future
import scala.util.{Failure, Random, Success}

/**
  * Created by john_liu on 2017/10/24.
  */

object myFuture {
  type CoffeeBeans = String
  type GroundCoffee = String
  case class Water(temperature: Int)
  type Milk = String
  type FrothedMilk = String
  type Espresso = String
  type Cappuccino = String

  //不使用Future
  def grind(beans: CoffeeBeans): GroundCoffee = s"ground coffee of $beans"
  def heatWater(water: Water): Water = water.copy(temperature = 85)
  def frothMilk(milk: Milk): FrothedMilk = s"frothed $milk"
  def brew(coffee: GroundCoffee, heatedWater: Water): Espresso = "espresso"
  def combine(espresso: Espresso, frothedMilk: FrothedMilk): Cappuccino = "cappuccino"

  case class GrindingException(msg: String) extends Exception(msg)
  case class FrothingException(msg: String) extends Exception(msg)
  case class WaterBoilingException(msg: String) extends Exception(msg)
  case class BrewingException(msg: String) extends Exception(msg)

  //使用Future
  def grind2(beans: CoffeeBeans): Future[GroundCoffee] = Future {
    println("start grinding...")
    Thread.sleep(Random.nextInt(2000))
    if (beans == "baked beans") throw GrindingException("are you joking?")
    println("finished grinding...")
    s"ground coffee of $beans"
  }

  def heatWater2(water: Water): Future[Water] = Future {
    println("heating the water now")
    Thread.sleep(Random.nextInt(2000))
    println("hot, it's hot!")
    water.copy(temperature = 85)
  }

  def frothMilk2(milk: Milk): Future[FrothedMilk] = Future {
    println("milk frothing system engaged!")
    Thread.sleep(Random.nextInt(2000))
    println("shutting down milk frothing system")
    s"frothed $milk"
  }

  def brew2(coffee: GroundCoffee, heatedWater: Water): Future[Espresso] = Future {
    println("happy brewing :)")
    Thread.sleep(Random.nextInt(2000))
    println("it's brewed!")
    "espresso"
  }

  def main(args: Array[String]): Unit = {
    //callback
    grind2("baked beans").onComplete {
      case Success(ground) => println(s"got my $ground")
      case Failure(ex) => println("This grinder needs a replacement, seriously!")
    }
    //mapping the future
    val temperatureOkay:Future[Boolean] = heatWater2(Water(25)).map{
      water=> (80 to 85).contains(water.temperature)
    }
    //keeping future flat
    def thetempratureOkay(water: Water):Future[Boolean] = Future{
      (80 to 85).contains(water.temperature)
    }
    val nestedFuture = heatWater2(Water(25)).map{
      water => thetempratureOkay(water)
    }
    val flatFuture  = heatWater2(Water(25)).flatMap(thetempratureOkay(_))
    //the example above can be aslo written like:
    val acceptable:Future[Boolean] = for {
      heatedwater <- heatWater2(Water(25))
      okay        <- thetempratureOkay(heatedwater)
    } yield okay
  }
  //using for yield
  def prepareCappucino():Future[Cappuccino]= {
    val groundCoffee = grind2("aribica beans")
    val heatedWater  = heatWater2(Water(20))
    val frontedMilk  = frothMilk2("milk")
    for {
      ground   <- groundCoffee
      water    <- heatedWater
      foam     <- frontedMilk
      espresso <- brew2(ground,water)
    } yield combine(espresso,foam)
  }

}
