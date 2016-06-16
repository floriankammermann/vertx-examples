package org.swisspush.vertx.examples;

import io.vertx.core.Vertx;
import io.vertx.ext.shell.ShellService;
import io.vertx.ext.shell.ShellServiceOptions;
import io.vertx.ext.shell.term.HttpTermOptions;

/**
 * Created by kammermannf on 15.06.2016.
 */
public class HttpShell {

    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();
        ShellService service = ShellService.create(vertx,
                new ShellServiceOptions().setHttpOptions(
                        new HttpTermOptions().
                                setHost("localhost").
                                setPort(8080)
                )
        );
        service.start();
    }
}
