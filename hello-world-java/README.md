# Run the Example with vertx Installation

* `cd vertx-examples/hello-world-java/src/main/java`
* `vertx run org/swisspush/vertx/examples/Greeter.java`
* <http://localhost:8080>

# Run the Example as fatJar

* `cd vertx-examples/hello-world-java`
* `gradle shadowJar`
* `java -jar build/libs/hello-world-0.0.1-SNAPSHOT-all.jar`
* <http://localhost:8080>

# Run the Example with 2 Instances

* `cd vertx-examples/hello-world-java/src/main/java`
* `vertx run org/swisspush/vertx/examples/Greeter.java --instances 2`
* `while true; clear; do curl http://localhost:8080; sleep 2; done`
* You will see, that the requests are distributed even, between the two instances
