package edu.csus.yaam.server.webapi;

import edu.csus.yaam.server.YaamConfig;
import lombok.RequiredArgsConstructor;
import spark.Spark;

/**
 * @author Ryan R
 * @date 3/30/2018
 */
@RequiredArgsConstructor
public class WebServerAPI {
    private final YaamConfig config;

    public void launch() {
        Spark.ipAddress(config.webApi.host);
        Spark.port(config.webApi.port);
        Spark.init();
    }
}