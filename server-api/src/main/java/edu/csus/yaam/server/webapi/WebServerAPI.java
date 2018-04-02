package edu.csus.yaam.server.webapi;

import edu.csus.yaam.server.YaamConfig;
import edu.csus.yaam.server.webapi.endpoint.Endpoint;
import edu.csus.yaam.server.webapi.endpoint.EndpointRoute;
import edu.csus.yaam.server.webapi.endpoints.ViewPursuits;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import spark.Spark;

/**
 * @author Ryan R
 * @date 3/30/2018
 */
@Log4j2
@RequiredArgsConstructor
public class WebServerAPI {
    private final YaamConfig config;

    public void launch() {
        Spark.ipAddress(config.webApi.host);
        log.info("Binding to port: " + config.webApi.port);
        Spark.port(config.webApi.port);
        Spark.initExceptionHandler(exception -> log.error("An exception occurred", exception));
        Spark.exception(Exception.class, (exception, request, response) -> {
            log.error("Exception on request: " + request.uri(), exception);
            response.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.body("");
        });

        // discourage snooping
        Spark.notFound((request, response) -> {
            response.status(HttpServletResponse.SC_FORBIDDEN);
            return "";
        });
        // add a authentication filter before every request, excluding /auth
        Spark.before(new AuthenticationFilter());
        Spark.before((request, response) -> log.info("Request: " + request.uri()));

        Endpoint[] endpoints = {
                new ViewPursuits()
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
        Spark.init();
    }
}