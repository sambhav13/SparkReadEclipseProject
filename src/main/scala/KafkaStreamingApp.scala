import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.SparkContext
import org.apache.spark.streaming.Seconds
import kafka.serializer.StringDecoder


//import org.apache.spark.streaming.kafka._
//import org.apache.spark.streaming.
class KafkaStreamingApp {

	
}

object KafkaStreamingApp{
  
  def main(args:Array[String])={

		val sc = new SparkConf().setAppName("KafkaStreaming App").setMaster("local")
		//val sparkCtx = new SparkContext(sc)
		val streamingCtx = new StreamingContext(sc,Seconds(5))

		val brokers = "localhost:9092"
		val kafkaParams = Map[String, String]("metadata.broker.list" -> brokers)

		val topicsSet = Set("test")

		val directKafkaStream = KafkaUtils.createDirectStream[
		                                                      String, String, StringDecoder, StringDecoder](
		                                                    		  streamingCtx, kafkaParams, topicsSet)
	
		                                                    		  
		 // Get the lines, split them into words, count the words and print
    val lines = directKafkaStream.map(_._2)
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x => (x, 1L)).reduceByKey(_ + _)
    wordCounts.print()

    // Start the computation
    streamingCtx.start()
    streamingCtx.awaitTermination()
		                                                    		  
		                                                    		  
    }
}