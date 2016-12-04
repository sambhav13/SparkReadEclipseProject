import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.scalatest.BeforeAndAfterAll
import org.scalatest.FunSpec
import org.apache.spark.streaming.StreamingContext

class SparkStreamingTest extends FunSpec with BeforeAndAfterAll{
  
  
  @transient private var _sc:SparkContext = _
  @transient private var _ssc:StreamingContext = _ 
  var obj:Test = null
  
  
  override def beforeAll(){
    _sc = new SparkContext(new SparkConf().setMaster("local[2]").setAppName("TestApp"))
    super.beforeAll()
    
    obj = new Test()
  }
  
  describe("rdd values") {

    it("values of rdd match") {
       assert(obj.rddMap(_sc).collect().toList === List(1,2,3,6,7))
    }

   
  }
  
  override def afterAll(){
   
    if(_sc!= null)
      _sc.stop()
    System.clearProperty("spark.driver.port")//rebind issue
    _sc = null
    super.afterAll()
  }
    
  
}