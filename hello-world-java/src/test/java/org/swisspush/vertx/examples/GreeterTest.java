package org.swisspush.vertx.examples;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import junit.framework.Assert;
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

        Greeter greeter = new Greeter();
        vertx.deployVerticle(greeter, result -> {
            if(result.succeeded()) {
                log.info("verticle deployed: " + result.result());
                when().get("/").then().assertThat().statusCode(200).body("greeting", equalTo("Hello Impacthub!"));
                async.complete();
            } else {
                log.error("failed to deploy verticle: " + result.cause());
                Assert.fail();
                async.complete();
            }
        });
    }
}
