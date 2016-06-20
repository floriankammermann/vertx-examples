package org.swisspush.vertx.examples;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.logging.LoggerFactory;

public class CryptoVerticleStarter {

    public static void main(String[] args) {

        Vertx.vertx().deployVerticle("org.swisspush.vertx.examples.CryptoVerticle", new DeploymentOptions().setInstances(2), event -> {
            LoggerFactory.getLogger(CryptoVerticleStarter.class).info("deployed Verticles: " + event.toString());
        });
    }
}
