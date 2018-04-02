package edu.csus.yaam.server.webapi.endpoint;

import spark.Route;
import spark.Spark;

/**
 * @author Ryan R
 * @date 4/1/2018
 */
public enum RequestMethod {
    GET {
        @Override
        public void connect(String path, Route route) {
            Spark.get(path, route);
        }
    },
    POST {
        @Override
        public void connect(String path, Route route) {
            Spark.post(path, route);
        }
    };

    public abstract void connect(String path, Route route);
}