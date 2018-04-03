package edu.csus.yaam.server.webapi.endpoint;

import spark.Request;
import spark.Response;

/**
 * @author Ryan R
 * @date 4/1/2018
 */
public interface Endpoint {
   void handle(Request request, Response response, EndpointContext context);
}