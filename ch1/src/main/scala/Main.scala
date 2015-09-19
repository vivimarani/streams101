package com.pk.test

import akka.actor.ActorSystem
import akka.event.{ Logging, LoggingAdapter }
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import scala.concurrent.ExecutionContext
import akka.stream.scaladsl._
import akka.util.ByteString
import akka.http.scaladsl._
import akka.http.scaladsl.model._
import scala.io.{Source => ScalaIO}
import java.io.File

object IteratorExample {
  implicit val as = ActorSystem()
  implicit val fm = ActorMaterializer()
  implicit val ex = as.dispatcher

  def fileLines(f: File): Iterator[String] = ScalaIO.fromFile(f).getLines

  def test1 = {
    val it = fileLines(new File("csv_files/1.csv"))
    (1 to 3) foreach { n =>
      println(s"next #$n ${it.next}")
      println(s"hasNext #$n ${it.hasNext}")
    }
  }
  
}
