package org.swisspush.vertx.examples;

import io.vertx.core.AbstractVerticle;

public class HelloJugsBern extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        vertx.createHttpServer().requestHandler(request -> {
            request.response().end("Hello Jugs Bern world !");
        }).listen(8080);
    }
}
