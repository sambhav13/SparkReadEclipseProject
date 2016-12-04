import org.scalatest.FunSuite
import com.holdenkarau.spark.testing.SharedSparkContext
import org.apache.spark.rdd.RDD


class SparkHoldenKarau extends FunSuite with SharedSparkContext {
  
  test("really simple transformation"){
    val input = List("hi","hi holden","bye")
    val expected = List(List("hi"),List("hi","holden"),List("bye"))
   val v =  sc.parallelize(input)
   
    assert(tokenize(sc.parallelize(input)).collect().toList===expected)
  }
  
  def tokenize(f:RDD[String]) = {
    f.map(_.split(" ").toList)
  }
}