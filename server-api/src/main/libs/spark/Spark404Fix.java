package spark;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.EnumSet;
import java.util.concurrent.CompletableFuture;
import javax.servlet.DispatcherType;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import spark.embeddedserver.jetty.EmbeddedJettyServer;
import spark.http.matching.MatcherFilter;
import spark.route.Routes;
import spark.staticfiles.StaticFilesConfiguration;

/**
 * Uses reflective calls to insert proper 404 error handling into the EmbeddedJettyServer when a websocket has been registered
 *
 * @author Concision
 * @date 4/4/2018
 */
public class Spark404Fix {
    /**
     * Call {@link Spark#awaitInitialization()} prior to attempting an injection
     */
    public static void inject() {
        try {
            // retrieve Spark instance
            Method getInstanceMethod = Spark.class.getDeclaredMethod("getInstance");
            getInstanceMethod.setAccessible(true);
            Service service = (Service) getInstanceMethod.invoke(null);

            // retrieve embedded server wrapper
            Field serverField = Service.class.getDeclaredField("server");
            serverField.setAccessible(true);
            Object embeddedServer = serverField.get(service);

            // ensure it is a instance of a EmbeddedJettyServer
            if (!(embeddedServer instanceof EmbeddedJettyServer)) {
                throw new UnsupportedOperationException("Only EmbeddedJettyServer is supported");
            }
            EmbeddedJettyServer embeddedJettyServer = (EmbeddedJettyServer) embeddedServer;

            // retrieve the real server
            Field jettyServerField = EmbeddedJettyServer.class.getDeclaredField("server");
            jettyServerField.setAccessible(true);
            Server server = (Server) jettyServerField.get(embeddedJettyServer);

            // steal some handlers
            HandlerList handler = (HandlerList) server.getHandler();
            Handler[] handlers = handler.getHandlers();

            // check if a websocket handler has been registered
            // index 0 is the basic web handler
            // index 1 only exists when there is a websocket registered
            if (2 <= handlers.length) {
                // retrieve handler
                Handler websocketHandler = handlers[1];
                ServletContextHandler websocketContextHandler = (ServletContextHandler) websocketHandler;
                // inject the default web handler
                websocketContextHandler.addFilter(
                        new FilterHolder(new MatcherFilter(Routes.create(), new StaticFilesConfiguration(), false, false)),
                        "/*",
                        EnumSet.of(DispatcherType.REQUEST)
                );
            }
        } catch (ReflectiveOperationException exception) {
            throw new RuntimeException("failed to inject ");
        }
    }

    public static void blockingInject() {
        Spark.awaitInitialization();
        inject();
    }

    public static void asyncInject() {
        CompletableFuture.runAsync(Spark404Fix::blockingInject);
    }
}