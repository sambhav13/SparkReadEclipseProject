import org.scalatest.FunSuite
import com.holdenkarau.spark.testing.RDDGenerator
import com.holdenkarau.spark.testing.SharedSparkContext

import org.scalacheck.Prop.forAll
import org.scalacheck.Arbitrary


class SparkTestingBase_FakeData extends FunSuite  with SharedSparkContext {
  test("map should not change number of elements"){ 
      forAll(RDDGenerator.genRDD[String](sc)(Arbitrary.arbitrary[String])) {
      rdd => rdd.map(_.length).count() == rdd.count()
    }
  }
}