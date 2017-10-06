package dry.toolbox.vertx.core.verticles;

import dry.toolbox.jdk.util.logging.SimpleLogger;
import static dry.toolbox.vertx.core.deploy.Deployer.*;
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
