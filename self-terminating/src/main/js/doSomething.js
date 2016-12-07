console.log("entered the verticle")
vertx.setPeriodic(2000, function() {
  console.log("Timer has fired");
  vertx.eventBus().publish("finished", "true");
});