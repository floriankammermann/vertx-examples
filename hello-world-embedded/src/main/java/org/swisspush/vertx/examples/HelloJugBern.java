package org.swisspush.vertx.examples;

import io.vertx.core.AbstractVerticle;

public class HelloJugBern extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        vertx.createHttpServer().requestHandler(request -> {
            request.response().end("Hello Jug Bern, I run in a tomcat\n\n\n" +
                                   "Here is my environment:\n"+System.getProperties());
        }).listen(8989);
    }
}
