import org.scalatest.FunSuite
import com.holdenkarau.spark.testing.SharedJavaSparkContext
import com.holdenkarau.spark.testing.DataFrameSuiteBase

import org.apache.spark.sql.SQLImplicits
//import org.apache.spark.sql.SQLContext

//import com.holdenkarau.spark.testing.  


class DataFrame_HoldenKarau extends FunSuite with  DataFrameSuiteBase {
   test("dataframe should be equal to its self"){
     
     val sqlCtx = sqlContext
     import sqlCtx.implicits._
     val input  = sc.parallelize(List(1,2,34)).toDF()
     assertDataFrameEquals(input,input)
   }
}