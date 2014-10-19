## Spark Testing Example Application

Source code for blog posts:

- [Running Spark tests in standalone cluster](http://eugenezhulenev.com/blog/2014/10/18/run-tests-in-standalone-spark-cluster/)

## Testing

By default tests are running with Spark in local mode

    sbt test

If you want to run tests in standalone Spark cluster you need to provide `spark.master` url

    sbt -Dspark.master=spark://spark-host:7777 test-assembled

## Building

In the root directory run:

    sbt assembly

The application fat jars will be placed in:
  - `target/scala-2.10/spark-testing-example-app.jar`


## Running

First you need to run `assembly` in sbt and then run java cmd

    java -Dspark.master=spark://spark-host:7777 spark-testing-example-app.jar
