import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.Encoders
import org.apache.spark.sql.Encoder
import org.apache.spark.sql.Dataset


//import org.apache.spark.sql.UDFRegistration
import org.apache.spark.sql.functions._
//import spark.imp

class ReadFile {
  
}
object ReadFile{
  
  def main(args:Array[String])={
    
    val sc = new SparkConf().setAppName("Reading csv file").setMaster("local[2]").set("spark.sql.warehouse.dir","file:///C:/tmp") 
    val ctx  = new SparkContext(sc)
   val sqlContext = new SQLContext(ctx)
    val cars = sqlContext.read.option("header", "true").csv("cars.csv")
    
    cars.printSchema()
    cars.collect().foreach { x => println(x) }
    
    import sqlContext.implicits._
   // val cars_ds = cars.as[(Int,String,String)]
   // val cars_dataset = cars.as[Car]
    println(cars.getClass())
    //println(cars_dataset.getClass())
    //println(cars.asInstanceOf[Car])
    
    
    
    
    val toInt    = udf[Int, String]( _.toInt)
    
    
      val changedCars = cars
        .withColumn("id", toInt(cars("id")))
        .select("id", "name", "color") 
  changedCars.printSchema()
  
 val custom =  changedCars.as[Car]
    custom.show()
    
  }
  
  
  case class Car(id:Int,name:String,color:String)
}

/*
val toDouble = udf[Double, String]( _.toDouble)
val toHour   = udf((t: String) => "%04d".format(t.toInt).take(2).toInt ) 
val days_since_nearest_holidays = udf( 
  (year:String, month:String, dayOfMonth:String) => year.toInt + 27 + month.toInt-12
 )*/