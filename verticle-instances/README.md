# Run the Example
* `cd vertx-examples/verticle-instances`
* `gradle shadowJar`
* `java -DcryptoInstances=1 -jar build/libs/verticles-0.0.1-SNAPSHOT-all.jar`
* `visualvm`
* x=2...max-cores+2
* `java -DcryptoInstances=x -jar build/libs/verticles-0.0.1-SNAPSHOT-all.jar`
* test-series(x=1...6) = {32,60,72,79,78,80} on Intel(R) Core(TM) i7-2620M CPU @ 2.70GHz
