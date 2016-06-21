package org.swisspush.vertx.examples;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class NonBlockingVerticle extends AbstractVerticle {

    private Logger log = LoggerFactory.getLogger(NonBlockingVerticle.class);

    @Override
    public void start() {

        vertx.createHttpServer().requestHandler(request -> {
            request.response().end("Hello Jugs Bern!");
        }).listen(8080);

    }

}
