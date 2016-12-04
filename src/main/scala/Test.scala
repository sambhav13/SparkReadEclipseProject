import org.apache.spark.rdd.RDD
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext



object Test{

	def main(args :Array[String]): Unit = {


		println("hello sam")
		val conf = new SparkConf().setAppName("Spark 2.0.0 App").setMaster("local[2]")
		val sc = new SparkContext(conf)
		val data = Array(1, 2, 3, 4, 5)
		val distData = sc.parallelize(data)
	
		distData.collect().foreach(x => println(x))
		distData.saveAsTextFile("NumberRDD");
	}
	
	
}

class Test{
  
  def rddMap(sc:SparkContext):RDD[Int]= {
	  
	  val rdd = sc.parallelize(List(1,2,3,6,7))
	  
	  return rdd
	}
}
