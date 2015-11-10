package com

import scala.concurrent.duration._
import scala.concurrent._
import scala.util.{Success, Failure, Try}
import scala.collection.immutable._
import akka.stream._
import akka.stream._
import akka.actor._
import akka.stream.actor._
import java.io.File
import java.nio.file.{Paths, Files}
import org.reactivestreams.Subscriber
import java.nio.charset.StandardCharsets


package object pkinsky {

  private val tZero = System.currentTimeMillis()

  def printlnC(s: Any): Unit = println(Console.GREEN + s + Console.RESET)
  def printlnE(s: Any): Unit = println(Console.RED + s + Console.RESET)

  def timedFuture[T](name: String)(f: Future[T])(implicit ec: ExecutionContext): Future[T] = {
    val start = System.currentTimeMillis()
    printlnC(s"--> started $name at t0 + ${start - tZero}")
    f.andThen{
      case Success(t) =>
        val end = System.currentTimeMillis()
        printlnC(s"\t<-- finished $name after ${end - start} millis")
      case Failure(ex) =>
        val end = System.currentTimeMillis()
        printlnE(s"\t<X> failed $name, total time elapsed: ${end - start}\n$ex")
    }
  }

  def withRetry[T](f: => Future[T], onFail: T, n: Int = 3)(implicit ec: ExecutionContext): Future[T] = 
    if (n > 0){ f.recoverWith{ case err: Exception => 
      printlnE(s"future failed with $err, retrying")
      withRetry(f, onFail, n - 1)
    }} else{
      printlnE(s"WARNING: failed to run future, substituting $onFail")
      Future.successful(onFail)
    }

}
