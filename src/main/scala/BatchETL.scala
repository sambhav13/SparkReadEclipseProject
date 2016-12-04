import org.apache.spark.sql.SparkSession

class BatchETL {
  
}
object BatchETL{
  def main(args:Array[String])={
    
    val spark = SparkSession.builder()
     .master("local")
     .appName("Word Count")
     .config("spark.sql.warehouse.dir","file:///C:/tmp") 
     .getOrCreate()
     
    val input = spark.read
                .format("json")             //Read from json file
                .load("source-path")
                
    val result = input
                  .select("device" , "signal")   //select some devices
                  .where("signal > 15")
                
         result.write
               .format("parquet")    //write to parquet file
               .save("dest-path")
                    
  }
}