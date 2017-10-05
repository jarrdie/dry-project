package dev.dryproject.vertx.core.verticles;

import dev.dryproject.jdk.util.logging.*;
import static dev.dryproject.vertx.core.deploy.Deployer.*;
import io.vertx.core.*;
import static io.vertx.core.Vertx.*;

public class HelloWorldVerticle extends AbstractVerticle {

    private static SimpleLogger logger = new SimpleLogger(HelloWorldVerticle.class.getSimpleName());

    @Override
    public void start() throws Exception {
        logger.line();
        logger.log("Verticle started");
        System.out.println("Hello world!!");
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Bye world!!");
        logger.log("Verticle stopped");
    }

    public static void main(String[] args) {
        Vertx vertx = vertx();
        String verticle = HelloWorldVerticle.class.getName();

        deploy(vertx, verticle);
        undeploy(vertx, verticle, 4000);
        shutdown(vertx, 8000);
    }

}
