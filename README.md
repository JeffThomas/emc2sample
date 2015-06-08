# emc2sample

## INSTALLING

This project requires command line Scala and sbt

http://www.scala-lang.org/download/install.html

http://www.scala-sbt.org/0.13/tutorial/index.html

Once those are installed and running simply clone this repository, go into the root and type `sbt`

## RUNNING THE TESTS

once you've launched sbt in the root directory type you can use `test` to run the full test suit.

```
> test
[info] FibonacciServiceSpec
[info]
[info] FibonacciService should
[info] + return a simple request for the first 10 fibonacci numbers
[info] + return a simple request for 0 numbers
[info] + return a simple request for a negative number without erroring
[info]
[info] Total for specification FibonacciServiceSpec
[info] Finished in 42 ms
[info] 3 examples, 0 failure, 0 error
[info] FibonacciSpec
...
```

## RUNNING THE SERVICE

At the sbt prompte use `re-start` to launch the service. You can then use a web browser to test the endpoint. The response is in `application/json` format. For example:

`http://localhost:8080/fibonacci/10`

`http://localhost:8080/fibonacci/1000`

You can then stop the service with `re-stop`.

## OTHER SBT COMMANDS

A sample of the other commands you can use.

`> clean` deletes all files created by a build, forcing them to be recreated

`> compile` builds but does not run

`> test-only` runs only one test. You can get a list of tests to run by typing `>test-only <tab>` You can also use `<tab>` for autocompletion.
