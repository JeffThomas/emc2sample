package com.fibonacci.services

import org.specs2.mutable.Specification

class FibonacciServiceSpec extends Specification {
   "FibonacciService" should {

     "return a simple request for the first 10 fibonacci numbers" in {
       val result = FibonacciService.getFib(10)
       result.length mustEqual 10
       result(9) mustEqual 34
     }

     "return a simple request for 0 numbers" in {
       val result = FibonacciService.getFib(0)
       result.length mustEqual 0
     }

     "return a simple request for a negative number without erroring" in {
       val result = FibonacciService.getFib(-10)
       result.length mustEqual 0
     }

   }
 }
