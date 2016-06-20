package org.swisspush.vertx.examples;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterVerticle extends AbstractVerticle {

    private Logger log = LoggerFactory.getLogger(CounterVerticle.class);

    private AtomicInteger counter = new AtomicInteger(0);

    public void start() {

        vertx.setPeriodic(10000, message -> {
            log.info(counter.get() + " ben hurs encrypted in 10sec");
            counter.set(0);
        });

        vertx.eventBus().consumer("ben.hur", message ->
                counter.incrementAndGet()
        );
    }
}
