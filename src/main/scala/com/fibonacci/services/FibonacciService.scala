package com.fibonacci.services

object FibonacciService {

  // Note that Scala's Stream has built in caching of previously calculated values.
  // This makes it very fast for repeated calling, but also eats up memory. Something to keep in mind.
  private val fib: Stream[BigInt] = 0 #:: 1 #:: fib.zip(fib.tail).map(p => p._1 + p._2)

  /**
   * Returns a List[BigInt] of the Fibonacci sequence up to n
   * @param n how much of the Fobonacci sequence to return
   * @return
   */
  def getFib(n:Int): List[BigInt] = {
    (fib take n).toList
  }
}

