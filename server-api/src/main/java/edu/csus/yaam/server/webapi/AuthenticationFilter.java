package edu.csus.yaam.server.webapi;

import spark.Filter;
import spark.Request;
import spark.Response;

/**
 * @author Ryan R
 * @date 4/1/2018
 */
public class AuthenticationFilter implements Filter {
    @Override
    public void handle(Request request, Response response) {
        // skip authentication check on /auth
        if (request.uri().startsWith("/auth")) {
            return;
        }

        // verify session is authenticated
        // Spark.halt(HttpServletResponse.SC_UNAUTHORIZED);
    }
}