package org.swisspush.vertx.examples;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.shiro.ShiroAuthOptions;
import io.vertx.ext.shell.ShellService;
import io.vertx.ext.shell.ShellServiceOptions;
import io.vertx.ext.shell.term.HttpTermOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;

import java.text.DateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * A {@link io.vertx.core.Verticle} which implements a simple, realtime,
 * multiuser chat. Anyone can connect to the chat application on port
 * 8000 and type messages. The messages will be rebroadcast to all
 * connected users via the @{link EventBus} Websocket bridge.
 *
 * @author <a href="https://github.com/InfoSec812">Deven Phillips</a>
 */
public class ChatServer extends AbstractVerticle {

  @Override
  public void start(Future<Void> startFuture) throws Exception {

    ShellService service = ShellService.create(vertx, new ShellServiceOptions().
            setHttpOptions(
                    new HttpTermOptions().
                            setHost("localhost").
                            setPort(8989).
                            setAuthOptions(new ShiroAuthOptions().
                                    setConfig(new JsonObject().put("properties_path", "auth.properties")))));
    service.start(ar -> {
      if (ar.succeeded()) {
        startFuture.succeeded();
      } else {
        startFuture.fail(ar.cause());
      }
    });

    Router router = Router.router(vertx);

    // Allow events for the designated addresses in/out of the event bus bridge
    BridgeOptions opts = new BridgeOptions()
      .addInboundPermitted(new PermittedOptions().setAddress("chat.to.server"))
      .addOutboundPermitted(new PermittedOptions().setAddress("chat.to.client"));

    // Create the event bus bridge and add it to the router.
    SockJSHandler ebHandler = SockJSHandler.create(vertx).bridge(opts);
    router.route("/eventbus/*").handler(ebHandler);

    // Create a router endpoint for the static content.
    router.route().handler(StaticHandler.create());

    // Start the web server and tell it to use the router to handle requests.
    vertx.createHttpServer().requestHandler(router::accept).listen(8080);

    EventBus eb = vertx.eventBus();

    // Register to listen for messages coming IN to the server
    eb.consumer("chat.to.server").handler(message -> {
      // Create a timestamp string
      String timestamp = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(Date.from(Instant.now()));
      // Send the message back out to all clients with the timestamp prepended.
      eb.publish("chat.to.client", timestamp + ": " + message.body());
    });

  }
}