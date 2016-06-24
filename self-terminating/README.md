# Self Terminated Vert.x 

To run the example you have to 
* download http://gradle.org/
* clone the repo
* navigate into the folder self-terminating
* execute `gradle shadowJar`
* execute `java -jar build/libs/self-terminating-0.0.1-SNAPSHOT-all.jar`

What happens:
* The class `SelfTerminated.java` deploys the js verticle doSomething.js, be aware that it is taken from the filesystem and not from the jar, so the jar has to be executed in the folder self-terminating
* The SelfTermianted class is waiting, until the doSomething verticle sends a message to the address finished
* SelfTerminated ends the java process it runs in

Possible improvements:
* Pass the path by arguments, where the js verticles are located
