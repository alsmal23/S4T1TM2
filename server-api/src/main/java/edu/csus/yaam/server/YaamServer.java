package edu.csus.yaam.server;

import edu.csus.yaam.server.db.SQLiteDatabase;
import edu.csus.yaam.server.webapi.AuthenticationFilter;
import edu.csus.yaam.server.webapi.endpoint.Endpoint;
import edu.csus.yaam.server.webapi.endpoint.EndpointRoute;
import edu.csus.yaam.server.webapi.endpoints.*;
import edu.csus.yaam.server.websocket.EchoWebSocket;
import javax.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import spark.Spark;
import spark.Spark404Fix;

/**
 * @author Ryan R
 * @date 4/1/2018
 */
@Log4j2
@Accessors(fluent = true)
public class YaamServer {
    @Getter
    private final YaamConfig config;
    @Getter
    private final SQLiteDatabase database;

    public YaamServer(YaamConfig config) {
        this.config = config;
        database = new SQLiteDatabase(config.sqliteFile);
    }

    public void launch() {
        log.info("Connecting to SQL database");
        this.connectSQL();
        log.info("Connected to SQL database");

        // construct WebAPI server
        log.info("Launching Spark WebAPI...");
        this.initializeSpark();
        log.info("Spark WebAPI launched");
    }


    private void connectSQL() {
        // load SQLite database
        try {
            database.connect();
        } catch (Throwable throwable) {
            log.fatal("Failed to connect to SQLite database", throwable);
            System.exit(-1);
        }
        try {
            database.initialize();
        } catch (Throwable throwable) {
            log.fatal("Failed to execute schema initialization statements", throwable);
            System.exit(-1);
        }
    }

    private void initializeSpark() {
        Spark.ipAddress(config.webApi.host);
        log.info("Binding to port: " + config.webApi.port);
        Spark.port(config.webApi.port);
        Spark.initExceptionHandler(exception -> {
            log.fatal("An exception occurred during initialization", exception);
            System.exit(-1);
        });

        // default Spark hooks
        Spark.exception(Exception.class, (exception, request, response) -> {
            log.error("Exception on request: " + request.uri(), exception);
            response.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.body("");
        });


        // register websockets
        Spark.webSocket("/websocket/echo", EchoWebSocket.class);


        // apply route mappings

        // discourage snooping
        Spark.notFound((request, response) -> {
            response.status(HttpServletResponse.SC_FORBIDDEN);
            return "";
        });

        // add a authentication filter before every request, excluding /auth
        Spark.before(new AuthenticationFilter());
        Spark.before((request, response) -> {
            if (!request.uri().equals("/robots.txt") && !request.uri().equals("/favicon.ico")) {
                log.info("Request: " + request.uri());
            }
        });

        // marks responses as JSON
        Spark.afterAfter((request, response) -> {
            if (!response.body().isEmpty()) {
                // try/catch abuse?!
                try {
                    new JSONObject(response.body());
                } catch (Throwable ignored) {
                    try {
                        new JSONArray(response.body());
                    } catch (Throwable ignored2) {
                        return;
                    }
                }
                response.type("application/json");
            }
        });

        // register endpoints
        Endpoint[] endpoints = new Endpoint[]{
				new ViewPursuits(database),
                new ViewProjects(database)
		};

        for (Endpoint endpoint : endpoints) {
            try {
                // create endpointer route wrapper
                EndpointRoute endpointRoute = new EndpointRoute(endpoint);
                endpointRoute.requestMethod().connect(endpointRoute.route(), endpointRoute);

                log.info("Registered route: " + endpointRoute.route());
            } catch (Throwable throwable) {
                log.error("Error registering endpoint", throwable);
            }
        }


        // Ensure Spark is launched, in the event endpoint registration is commented out
        Spark.init();
        Spark404Fix.blockingInject();
    }
}