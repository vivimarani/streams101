name          := "streams-mini"
version       := "0.0.1"
scalaVersion  := "2.11.6"
scalacOptions := Seq("-unchecked", "-feature", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaStreamV      = "1.0"
  Seq(
    "com.typesafe.akka" %% "akka-stream-experimental"             % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-core-experimental"          % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-spray-json-experimental"    % akkaStreamV
  )
}

resolvers ++= Seq(
  "Confluentic repository" at "http://packages.confluent.io/maven/"
)


initialCommands := """|import akka.actor._
                      |import akka.stream.scaladsl._
                      |import akka.util._
                      |import scala.concurrent._
                      |import scala.concurrent.duration._""".stripMargin
