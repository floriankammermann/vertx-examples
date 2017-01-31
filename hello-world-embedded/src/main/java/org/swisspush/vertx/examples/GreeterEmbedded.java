package org.swisspush.vertx.examples;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class GreeterEmbedded extends AbstractVerticle {

    @Override
    public void start(Future<Void> fut) throws Exception {
        vertx.createHttpServer().requestHandler(request -> {
            request.response().end("Hello jug, I run in a tomcat\n\n\n" +
                                   "Here is my environment:\n"+System.getProperties());
        }).listen(8989, result -> {
            if (result.succeeded()) {
                fut.complete();
            } else {
                fut.fail(result.cause());
            }
        });
    }
}
