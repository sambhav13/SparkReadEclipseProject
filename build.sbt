name := "SparkReadProj"

version := "1.0"

scalaVersion := "2.10.6"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.0.0"
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.0.0"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.0.0"
libraryDependencies += "com.holdenkarau" % "spark-testing-base_2.10" % "2.0.0_0.4.7"
libraryDependencies += "org.apache.spark" %% "spark-hive" % "2.0.0"
libraryDependencies += "org.apache.spark" % "spark-streaming-kafka_2.10" % "1.6.2"



lazy val sscheckVersion = "0.2.3"
libraryDependencies += "es.ucm.fdi" %% "sscheck" % sscheckVersion
resolvers += Resolver.bintrayRepo("juanrh", "maven")

test in assembly := {}