package org.swisspush.vertx.examples;

import io.vertx.core.Handler;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VertxHandlerTest {

    private void addition(int value1, int value2, Handler<Integer> handler) {
        Integer result = value1 + value2;
        handler.handle(result);
    }

    /* example with anonymous innerclass */
    @Test
    public void testAdd() {
        addition(1, 2, new Handler<Integer>() {
            @Override
            public void handle(Integer event) {
                assertEquals(Integer.valueOf(3), event);
            }
        });
    }

    /* example with lamda, java 8 */
    @Test
    public void testAddLambda() {
        addition(1,2, t -> assertEquals(Integer.valueOf(3), t));
    }
}
