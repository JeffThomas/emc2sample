package com.fibonacci.controllers

import akka.actor.Actor

import com.typesafe.config.ConfigFactory

import spray.http.MediaTypes._
import spray.http.StatusCodes
import spray.routing._
import spray.json._
import DefaultJsonProtocol._

import com.fibonacci.services.FibonacciService

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class FibonacciServiceActor extends Actor with Fibonacci {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(myRoute)
}


// this trait defines our service behavior independently from the service actor
trait Fibonacci extends HttpService {
  private val conf = ConfigFactory.load()
  private val maxNumber = conf.getInt("fibonacci.maxNumbers")

  val myRoute =
    path("fibonacci" / IntNumber) { count =>
      get {
        if (count > maxNumber) {
          complete(StatusCodes.BadRequest, s"Numbers larger than $maxNumber are not supported.")
        } else {
          val result: List[BigInt] = FibonacciService.getFib(count)
          respondWithMediaType(`application/json`) {
            complete {
              result.toJson.prettyPrint
            }
          }
        }
      }
    } ~
      pathPrefix("fibonacci" / "-") {
        complete(StatusCodes.BadRequest, "Negative numbers are not supported.")
      }

}