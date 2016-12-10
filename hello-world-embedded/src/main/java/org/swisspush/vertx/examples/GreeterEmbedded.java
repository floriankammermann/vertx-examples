package org.swisspush.vertx.examples;

import io.vertx.core.AbstractVerticle;

public class GreeterEmbedded extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        vertx.createHttpServer().requestHandler(request -> {
            request.response().end("Hello Impacthub, I run in a tomcat\n\n\n" +
                                   "Here is my environment:\n"+System.getProperties());
        }).listen(8989);
    }
}
