# Run the Example
* `cd vertx-examples/verticle-worker`
* `gradle shadowJar`
* `java -DworkerVerticle=false -DworkerInstances=1 -jar build/libs/verticle-worker-0.0.1-SNAPSHOT-all.jar`
* `while true; clear; do curl http://localhost:8080; sleep 0.3; done`
* `visualvm`
