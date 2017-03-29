# Run the Example one JVM
* `cd vertx-examples/eventbus-chat`
* `gradle shadowJar`
* `java -jar build/libs/eventbus-chat-0.0.1-SNAPSHOT-all.jar`
* <http://localhost:8080> test in two differnt Browsers with different tabs

# Run the Example one two JVM clustered eventbus
* `cd vertx-examples/eventbus-chat`
* `gradle shadowJar`
* `java -jar build/libs/eventbus-chat-0.0.1-SNAPSHOT-all.jar`
* `java -DshellPort=8999 -DhttpPort=8181 -jar build/libs/eventbus-chat-0.0.1-SNAPSHOT-all.jar -cluster`
* <http://localhost:8080/index8080.html> 
* <http://localhost:8181/index8181.html>
