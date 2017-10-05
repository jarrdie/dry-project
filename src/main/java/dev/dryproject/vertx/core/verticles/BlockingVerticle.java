package dev.dryproject.vertx.core.verticles;

import dev.dryproject.jdk.util.logging.*;
import static dev.dryproject.vertx.core.deploy.Deployer.*;
import io.vertx.core.*;
import static io.vertx.core.Vertx.*;

public class BlockingVerticle extends AbstractVerticle {

    private static SimpleLogger logger = new SimpleLogger(BlockingVerticle.class.getSimpleName());

    @Override
    public void start() throws Exception {
        logger.line();
        logger.log("Verticle started");
        System.out.println("Blocking the event loop :( !!");
        System.out.println("A warning like this will be throw by Vert.x:");
        Thread.currentThread().sleep(3000);
    }

    public static void main(String[] args) {
        Vertx vertx = vertx();
        String verticle = BlockingVerticle.class.getName();

        deploy(vertx, verticle);
        shutdown(vertx, 8000);
    }

}
