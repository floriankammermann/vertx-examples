package org.swisspush.vertx.examples;

import io.vertx.core.AbstractVerticle;

public class HelloJugsBern extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        vertx.createHttpServer().requestHandler(request -> {
            request.response()
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end("{\"greeting\":\"Hello Jugs Bern!\"}");
        }).listen(8080);
    }
}
