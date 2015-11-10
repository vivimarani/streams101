name          := "streams-mini"

version       := "0.0.1"

scalaVersion  := "2.11.6"

scalacOptions := Seq("-unchecked", "-feature", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaStreamV      = "1.0"
  Seq(
    "com.typesafe.akka" %% "akka-stream-experimental"             % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-core-experimental"          % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-spray-json-experimental"    % akkaStreamV,
    "net.databinder.dispatch" %% "dispatch-core" % "0.11.2",
    "net.databinder.dispatch" %% "dispatch-json4s-native" % "0.11.2"
  )
}

resolvers ++= Seq(
  "Confluentic repository" at "http://packages.confluent.io/maven/"
)


initialCommands := """|import akka.actor._
                      |import akka.stream.scaladsl._
                      |import akka.stream._
                      |import akka.stream.io._
                      |import akka.util._
                      |import java.io.File
                      |import scala.util._
                      |import scala.concurrent._
                      |import scala.language.postfixOps
                      |implicit val as = ActorSystem()
                      |implicit val mat = ActorMaterializer()
                      |implicit val ec = as.dispatcher
                      |import com.pkinsky.RedditAPIImpl._
                      |import com.pkinsky._
                      |import scala.concurrent.duration._""".stripMargin
