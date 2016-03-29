name := "samza-bloomfilter-store"

appVersion := "0.1"

val samzaVersion = "0.10.0.8-indix"
val samzaApi = "org.apache.samza" % "samza-api" % samzaVersion
val samzaCore = "org.apache.samza" % "samza-core_2.10" % samzaVersion
val samzaYarn = "org.apache.samza" % "samza-yarn_2.10" % samzaVersion
val samzaKafka = "org.apache.samza" % "samza-kafka_2.10" % samzaVersion
val kafka = "org.apache.kafka" % "kafka_2.10" % "0.8.2.1"
val hadoopHdfs = "org.apache.hadoop" % "hadoop-hdfs" % "2.4.0"
val hadoopAws = "org.apache.hadoop" % "hadoop-aws" % "2.6.0"
val samzaLog4j = "org.apache.samza" % "samza-log4j" % samzaVersion
val slf4jLog4j12 = "org.slf4j" % "slf4j-log4j12" % "1.6.2" % "runtime"
val samzaTest = "org.apache.samza" % "samza-test_2.10" % samzaVersion

val scalaTest = "org.scalatest" %% "scalatest" % "2.2.4" % "test"
val mockito = "org.mockito" % "mockito-all" % "1.9.5"

val excludeDepsForLive = Seq("junit.*", "jets3t.*")

lazy val commonSettings = Seq(
  organization := "samza.contrib.store",
  version := appVersion,
  scalaVersion := "2.10.4",
  fork in run := false,
  resolvers ++= Seq(
    Resolver.sonatypeRepo("snapshots"),
    "Indix Maven" at "http://artifacts.indix.tv:8081/artifactory/libs-release",
    "morphia.googlecode.com" at "http://morphia.googlecode.com/svn/mavenrepo/"
  ),
  parallelExecution in This := false,
  publishMavenStyle := true,
  crossPaths := true,
  publishArtifact in Test := false,
  publishArtifact in(Compile, packageDoc) := false,
  publishArtifact in(Compile, packageSrc) := true,

  publishTo <<= version { v =>
    if (appVersion.endsWith("-SNAPSHOT"))
      Some("Indix Snapshot Artifactory" at "http://artifacts.indix.tv:8081/artifactory/libs-snapshot-local")
    else
      Some("Indix Release Artifactory" at "http://artifacts.indix.tv:8081/artifactory/libs-release-local")
  },
  credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(net.virtualvoid.sbt.graph.Plugin.graphSettings: _*).
  settings(
    name := "samza-bloomfilter-store",
    libraryDependencies ++= Seq(
      scalaTest, mockito
    )
  )  
