package edu.csus.yaam.server.webapi.endpoints;

import edu.csus.yaam.server.webapi.endpoint.APIEndpoint;
import edu.csus.yaam.server.webapi.endpoint.Endpoint;
import edu.csus.yaam.server.webapi.endpoint.EndpointContext;
import edu.csus.yaam.server.webapi.endpoint.RequestMethod;
import java.util.UUID;
import spark.Request;
import spark.Response;

/**
 * @author Ryan R
 * @date 4/1/2018
 */
@APIEndpoint(route = "/project/{project:UUID}/pursuits", method = RequestMethod.GET)
public class ViewPursuits implements Endpoint {
    @Override
    public void handle(Request request, Response response, EndpointContext context) {
       UUID project = context.routeArgument("project");

       response.body(project.toString());
    }
}