package org.swisspush.vertx.examples;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.logging.LoggerFactory;

public class WorkerVerticleStarter {

    public static void main(String[] args) {

        int workerInstances = 1;
        try {
            workerInstances = Integer.parseInt(System.getProperty("workerInstances"));
        } catch (Exception e) {
            // do nothing, take default instances
        }

        boolean workerVerticle = false;
        try {
            workerVerticle = Boolean.parseBoolean(System.getProperty("workerVerticle"));
        } catch (Exception e) {
            // do nothing, take default instances
        }

        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle("org.swisspush.vertx.examples.BlockingVerticle",
                new DeploymentOptions().setInstances(workerInstances).setWorker(workerVerticle), eventB -> {
            LoggerFactory.getLogger(WorkerVerticleStarter.class).info("deployed Blocking Verticle: " + eventB.toString());
            vertx.deployVerticle("org.swisspush.vertx.examples.NonBlockingVerticle", eventNB -> {
                LoggerFactory.getLogger(WorkerVerticleStarter.class).info("deployed NonBlocking Verticle: " + eventNB.toString());
            });
        });
    }
}
