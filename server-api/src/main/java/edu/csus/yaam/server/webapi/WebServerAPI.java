package edu.csus.yaam.server.webapi;

import edu.csus.yaam.server.YaamConfig;
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
        Spark.port(config.webApi.port);
        Spark.initExceptionHandler(exception -> log.error("An exception occurred", exception));

        // add a authentication filter before every request, excluding /auth
        Spark.before(new AuthenticationFilter());

        Spark.init();
    }
}