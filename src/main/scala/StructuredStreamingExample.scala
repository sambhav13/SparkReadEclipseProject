
import org.apache.spark.sql.functions._
import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf




class StructuredStreamingExample {
  
}

object StructuredStreamingExample{
  
  def main(args:Array[String])={
    
       val sc = new SparkConf().set("spark.sql.warehouse.dir","file:///C:/tmp")
        val spark = SparkSession
        .builder
        .appName("StructuredNetworkWordCount")
        .master("local[2]")
        .config(sc)
        .getOrCreate()
        
        import spark.implicits._
        
        // Create DataFrame representing the stream of input lines from connection to localhost:9999
        val lines = spark.readStream
          .format("socket")
          .option("host", "localhost")
          .option("port", 9999)
          // .option("spark.sql.warehouse.dir", "file:///C:/Users/sambhav/scalaWorkspace/SparkReadProj/spark-warehouse")
          //.option("spark.sql.warehouse.dir", "file:///C:\\Users\\sambhav\\scalaWorkspace\\SparkReadProj\\spark-warehouse")
          .load()
        
        // Split the lines into words
        val words = lines.as[String].flatMap(_.split(" "))
        
        // Generate running word count
        val wordCounts = words.groupBy("value").count()
        
        // Group the data by window and word and compute the count of each group
        val windowedCounts = words.groupBy(
          window($"timestamp", "10 seconds", "5 seconds"),
          $"word"
        ).count()
        
        val query = windowedCounts.writeStream.outputMode("complete").format("console").start()
       
        // Start running the query that prints the running counts to the console
       /* val query = wordCounts.writeStream
          .outputMode("complete")
          .format("console")
          .start()*/
        //wordCounts.collect().foreach { x => println(x) } 
        query.awaitTermination()
    }
  
}