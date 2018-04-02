package edu.csus.yaam.server.webapi.endpoint;

import java.util.Map;

/**
 * @author Ryan R
 * @date 4/1/2018
 */
public class EndpointContext {
    private final Map<String, Object> arguments;

    public EndpointContext(Map<String, Object> arguments) {
        this.arguments = arguments;
    }

    public <T> T getRouteArgument(String variableName) {
        return (T) arguments.get(variableName);
    }
}