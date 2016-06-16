package org.swisspush.vertx.examples;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.shiro.ShiroAuthOptions;
import io.vertx.ext.shell.ShellService;
import io.vertx.ext.shell.ShellServiceOptions;
import io.vertx.ext.shell.term.HttpTermOptions;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
public class DeployShell extends AbstractVerticle {

  @Override
  public void start(Future<Void> startFuture) throws Exception {
    ShellService service = ShellService.create(vertx, new ShellServiceOptions().
            setHttpOptions(
                    new HttpTermOptions().
                            setHost("localhost").
                            setPort(8080).
                            setAuthOptions(new ShiroAuthOptions().
                                    setConfig(new JsonObject().put("properties_path", "auth.properties")))));
    service.start(ar -> {
      if (ar.succeeded()) {
        startFuture.succeeded();
      } else {
        startFuture.fail(ar.cause());
      }
    });
  }
}
