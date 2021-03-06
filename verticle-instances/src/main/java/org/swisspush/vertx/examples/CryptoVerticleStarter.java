package org.swisspush.vertx.examples;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.logging.LoggerFactory;

public class CryptoVerticleStarter {

    public static void main(String[] args) {

        int cryptoInstances = 1;
        try {
            cryptoInstances = Integer.parseInt(System.getProperty("cryptoInstances"));
        } catch (Exception e) {
            // do nothing, take default instances
        }
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle("org.swisspush.vertx.examples.CryptoVerticle", new DeploymentOptions().setInstances(cryptoInstances), eventCrypto -> {
            LoggerFactory.getLogger(CryptoVerticleStarter.class).info("deployed Crypto Verticles: " + eventCrypto.toString());
            vertx.deployVerticle("org.swisspush.vertx.examples.CounterVerticle", new DeploymentOptions().setInstances(1), eventCounter -> {
                LoggerFactory.getLogger(CryptoVerticleStarter.class).info("deployed Counter Verticles: " + eventCounter.toString());
            });
        });
    }
}
