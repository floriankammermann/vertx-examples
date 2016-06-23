package org.swisspush.vertx.examples;

import io.vertx.core.AbstractVerticle;

public class HelloJugBern extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        vertx.createHttpServer().requestHandler(request -> {
            request.response().end("Hello Jugs Bern!");
        }).listen(8989);
    }
}
