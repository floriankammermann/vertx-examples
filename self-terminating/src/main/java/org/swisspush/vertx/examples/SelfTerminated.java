package org.swisspush.vertx.examples;

import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class SelfTerminated {

    private static Logger  log = LoggerFactory.getLogger(SelfTerminated.class);

    private static Vertx vertx;

    public static void main(String[] args) {
        vertx = Vertx.vertx();
        vertx.deployVerticle("src/main/js/doSomething.js", deployedEvent -> {
            log.info("deployed verticle: " + deployedEvent.result());
            vertx.eventBus().consumer("finished", finishedMessage -> {
                log.info("job is finished will undeploy the verticle");
                vertx.undeploy(deployedEvent.result(), undeployEvent -> {
                    log.info("will exit, nothing more to do here");
                    System.exit(0);
                });
            });
        });
    }
}
