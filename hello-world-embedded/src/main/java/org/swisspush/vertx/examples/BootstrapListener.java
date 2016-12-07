/*
 * ------------------------------------------------------------------------------------------------
 * Copyright 2013 by Swiss Post, Information Technology Services
 * ------------------------------------------------------------------------------------------------
 * $Id$
 * ------------------------------------------------------------------------------------------------
 */
package org.swisspush.vertx.examples;

import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class BootstrapListener implements ServletContextListener {

    private static final Logger log = LoggerFactory.getLogger(BootstrapListener.class);

    @Override
    public void contextInitialized(ServletContextEvent context) {

        Vertx.vertx().deployVerticle("org.swisspush.vertx.examples.HelloJugBern", result -> {
            if (result.succeeded()) {
                String deploymentID = result.result();
                log.debug("Module has been deployed with deploymentID {}", deploymentID);
            } else {
                throw new RuntimeException("Module not started: {}", result.cause());
            }
        });

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
