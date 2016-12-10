package org.swisspush.vertx.examples;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Created by florian kammermann on 14.06.2016.
 */
@RunWith(VertxUnitRunner.class)
public class GreeterTest {

    private Logger log = LoggerFactory.getLogger(GreeterTest.class);

    @Test
    public void responseTest(TestContext context) {
        Vertx vertx = Vertx.vertx();
        Async async = context.async();

        // RestAssured Configuration
        RestAssured.port = 8080;
        RestAssured.registerParser("application/json; charset=utf-8", Parser.JSON);
        RestAssured.defaultParser = Parser.JSON;

        Greeter helloJugsBern = new Greeter();
        vertx.deployVerticle(helloJugsBern, context.asyncAssertSuccess(assertSuccess -> {
            log.info("verticle deployed: " + assertSuccess);
            when().get("/").then().assertThat().statusCode(200).body("greeting", equalTo("Hello Jug Bern!"));
            async.complete();
        }));
    }
}
