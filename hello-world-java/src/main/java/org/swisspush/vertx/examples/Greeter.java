package org.swisspush.vertx.examples;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class Greeter extends AbstractVerticle {

    @Override
    public void start(Future<Void> fut) throws Exception {
        vertx.createHttpServer().requestHandler(request -> {
            request.response()
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end("{\"greeting\":\"Hello jug!\", \"thread\":\""+Thread.currentThread().getName()+"\"}");
        }).listen(8080, result -> {
            if (result.succeeded()) {
                fut.complete();
            } else {
                fut.fail(result.cause());
            }
        });
    }
}
