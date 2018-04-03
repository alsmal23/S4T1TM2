package edu.csus.yaam.server.webapi.endpoint;

import edu.csus.yaam.server.util.BlockChain;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * @author Ryan R
 * @date 4/1/2018
 */
@Accessors(fluent = true)
public class EndpointRoute implements Route {
    private static final Pattern ROUTE_VARIABLES = Pattern.compile("(\\*|\\{(.*?)(:(.*?))?})");
    private static final Map<String, Function<String, ?>> VARIABLE_TYPES = new HashMap<>();

    static {
        VARIABLE_TYPES.put("UUID", UUID::fromString);
    }

    private final Endpoint endpoint;
    @Getter
    private String route;
    @Getter
    private RequestMethod requestMethod;

    private Variable[] variables;

    @RequiredArgsConstructor
    private static class Variable {
        private final int index;
        private final String name;
        private final Function<String, ?> converter;
    }

    public EndpointRoute(Endpoint endpoint) {
        this.endpoint = endpoint;

        // verify and retrieve declared APIEndpoint
        Class<? extends Endpoint> endpointClass = endpoint.getClass();
        if (!endpointClass.isAnnotationPresent(APIEndpoint.class)) {
            throw new IllegalStateException("Endpoint " + endpointClass + " has no @APIEndpoint annotation declaration");
        }

        // apply APIEndpoint properties
        APIEndpoint apiEndpoint = endpointClass.getDeclaredAnnotation(APIEndpoint.class);

        requestMethod = apiEndpoint.method();

        // compile route
        BlockChain<Variable> variables = new BlockChain<>();
        Matcher matcher = ROUTE_VARIABLES.matcher(apiEndpoint.route());
        StringBuffer routeBuffer = new StringBuffer();
        for (int i = 0; matcher.find(); i++) {
            matcher.appendReplacement(routeBuffer, "*");

            if (!"*".equals(matcher.group(0))) {
                Function<String, ?> converter;
                if (matcher.group(3) != null) {
                    converter = VARIABLE_TYPES.get(matcher.group(4));
                    if (converter == null) {
                        throw new IllegalStateException("Endpoint " + endpointClass + " declared a unknown route variable type: " + matcher.group(3));
                    }
                } else {
                    converter = Function.identity();
                }

                variables.push(new Variable(i, matcher.group(2), converter));
            }
        }
        matcher.appendTail(routeBuffer);

        this.route = routeBuffer.toString();
        this.variables = variables.toArray(new Variable[0]);
    }


    /**
     * Delegates request to endpoint after extracting authentication session
     */
    @Override
    public Object handle(Request request, Response response) {
        // TODO: Extract authentication session

        // extract variable types
        String[] splat = request.splat();

        Map<String, Object> arguments = new HashMap<>();
        for (Variable variable : variables) {
            try {
                arguments.put(variable.name, variable.converter.apply(splat[variable.index]));
            } catch (Throwable throwable) {
                throw new RuntimeException("Endpoint " + endpoint.getClass() + " route variable '" + variable.name + "' failed conversion for request: " + splat[variable.index], throwable);
            }
        }


        EndpointContext context = new EndpointContext(arguments);

        endpoint.handle(request, response, context);

        return response.body();
    }
}