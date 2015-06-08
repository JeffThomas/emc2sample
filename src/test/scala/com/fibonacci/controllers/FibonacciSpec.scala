package com.fibonacci.controllers

import com.typesafe.config.ConfigFactory
import org.specs2.mutable.Specification
import spray.http.StatusCodes._
import spray.http._
import spray.testkit.Specs2RouteTest

class FibonacciSpec extends Specification with Specs2RouteTest with Fibonacci {
  def actorRefFactory = system
  
  "MyService" should {

    "return a simple request for the first 10 fibonacci numbers" in {
      Get("/fibonacci/10") ~> myRoute ~> check {
        responseAs[String] must contain("[0, 1, 1, 2, 3, 5, 8, 13, 21, 34]")
      }
    }

    "return a simple request for the first 2 fibonacci numbers" in {
      Get("/fibonacci/2") ~> myRoute ~> check {
        responseAs[String] must contain("[0, 1]")
      }
    }

    "leave GET requests to other paths unhandled" in {
      Get("/kermit") ~> myRoute ~> check {
        handled must beFalse
      }
    }

    "return a MethodNotAllowed error for PUT requests to the root path" in {
      Put("/fibonacci/10") ~> sealRoute(myRoute) ~> check {
        status === MethodNotAllowed
        responseAs[String] === "HTTP method not allowed, supported methods: GET"
      }
    }

    "return a BadRequest for a negative number" in {
      Put("/fibonacci/-10") ~> sealRoute(myRoute) ~> check {
        status === BadRequest
        responseAs[String] === "Negative numbers are not supported."
      }
    }

    "return a BadRequest for a too large number" in {
      val conf = ConfigFactory.load()
      val maxNumber = conf.getInt("fibonacci.maxNumbers")

      Get("/fibonacci/" + maxNumber + 1) ~> sealRoute(myRoute) ~> check {
        status === BadRequest
        (responseAs[String]).startsWith("Numbers larger than")
      }
    }
  }
}
