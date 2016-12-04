import es.ucm.fdi.sscheck.gen.RDDGen
import org.scalacheck.Prop
import org.apache.spark.rdd.RDD
import org.scalatest.enablers.Length

import org.scalacheck.Arbitrary.arbitrary



class ssCheck_FakeDataHoldKarau {
   
  //val RDDGen
  def forallRDDFenOfNtoM = {
     val minWords,maxWords = (50,100)
    /* Prop.forAll( RDDGen.ofNtoM(50,100,arbitrary[String]) ){
       rdd:RDD[String] => rdd.map(_.length()).sum must be_>= (0.0)
     }*/
  }
}