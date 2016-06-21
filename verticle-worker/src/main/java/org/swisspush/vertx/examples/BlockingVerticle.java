package org.swisspush.vertx.examples;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class BlockingVerticle extends AbstractVerticle {

    private Logger log = LoggerFactory.getLogger(BlockingVerticle.class);

    @Override
    public void start() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.info("got interupted: " + e.getMessage());
        }

        vertx.setPeriodic(7000, event -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                log.info("got interupted: " + e.getMessage());
            }
        });


    }

}
