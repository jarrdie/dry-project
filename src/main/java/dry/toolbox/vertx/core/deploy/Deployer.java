package dry.toolbox.vertx.core.deploy;

import dry.toolbox.jdk.util.logging.SimpleLogger;
import io.vertx.core.*;
import java.util.*;
import java.util.concurrent.*;

public class Deployer {

    private static SimpleLogger logger = new SimpleLogger(Deployer.class.getSimpleName());
    private static Map<String, String> deployments = new ConcurrentHashMap<>();

    public static void deploy(Vertx vertx, String verticle) {
        vertx.deployVerticle(verticle, deployment -> {
            if (!deployment.succeeded()) {
                logger.log("Deployment failed!");
                return;
            }
            String id = deployment.result();
            logger.log("Deployment succeed with id: " + id);
            deployments.put(id, verticle);
        });
    }

    public static void undeploy(Vertx vertx, String verticle, int msWait) {
        vertx.setTimer(msWait, timer -> {
            undeploy(vertx, verticle);
        });

    }

    public static void undeploy(Vertx vertx, String verticle) {
        String id = findId(verticle);
        vertx.undeploy(id, undeployment -> {
            if (!undeployment.succeeded()) {
                logger.log("Undeployment failed!");
                return;
            }
            logger.log("Undeployment succeed");
        });
    }

    public static String findId(String verticle) {
        for (Map.Entry<String, String> deployment : deployments.entrySet()) {
            String id = deployment.getKey();
            String currentVerticle = deployment.getValue();
            if (currentVerticle.equals(verticle)) {
                return id;
            }
        }
        return "";
    }

    public static void shutdown(Vertx vertx, int msWait) {
        vertx.setTimer(msWait, timer -> {
            shutdown();
        });
    }

    public static void shutdown() {
        logger.log("Jvm shut down");
        System.exit(0);
    }

}
