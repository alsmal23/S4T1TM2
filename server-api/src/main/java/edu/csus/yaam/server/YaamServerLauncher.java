package edu.csus.yaam.server;


import edu.csus.yaam.server.webapi.WebServerAPI;
import edu.csus.yaam.server.websocket.WebSocketServer;
import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import lombok.extern.log4j.Log4j2;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentGroup;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

/**
 * Procedurally starts a YAAM server
 *
 * @author Ryan R
 * @date 3/8/2018
 */
@Log4j2
public class YaamServerLauncher {
    public static void main(String... args) {
        // build argument parser
        ArgumentParser parser = ArgumentParsers.newFor("TaskManager")
                .addHelp(false)
                .build()
                .defaultHelp(true)
                .description("YAAM WebServer");

        // add additional parameters
        ArgumentGroup optionalArguments = parser.addArgumentGroup("Optional Arguments");
        // allow specifying a configuration file by a filepath
        optionalArguments.addArgument("--conf", "--config")
                .dest("configFile")
                .nargs(1)
                .type(File.class)
                .metavar("config.yml")
                .setDefault(new File("yaam-config.yml"))
                .help("Specifies a configuration file by filepath");
        // use no external configuration file, use bundled config
        optionalArguments.addArgument("--noConfig")
                .dest("noConfig")
                .type(boolean.class)
                .help("Uses default bundled config");


        // parse arguments
        Namespace namespace = new Namespace(new HashMap<>());
        try {
            parser.parseArgs(args, namespace.getAttrs());
        } catch (ArgumentParserException exception) {
            System.err.println("error: " + exception.getMessage());
            System.err.println();
            parser.printHelp(new PrintWriter(System.err, true));
            System.exit(-1);
        }


        // load YAAM server configuration and settings into memory
        YaamConfig config = YaamConfig.load(namespace);
        log.info("Loaded configuration");


        // construct WebAPI
        log.info("Launching web api...");
        try {
            WebServerAPI apiServer = new WebServerAPI(config);
            apiServer.launch();
        } catch (Throwable throwable) {
            log.fatal("Failed to launch WebServerAPI", throwable);
            System.exit(-1);
        }
        log.info("Web API launched");

        // construct WebSocket
        log.info("Launching web socket server...");
        try {
            WebSocketServer webSocketServer = new WebSocketServer(config);
            webSocketServer.launch();
        } catch (Throwable throwable) {
            log.fatal("Failed to launch WebSocketServer", throwable);
            System.exit(-1);
        }
        log.info("Web socket server launched");


        log.info("Server running");
    }
}