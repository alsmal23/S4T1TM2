package edu.csus.yaam.server;


import edu.csus.yaam.server.db.SQLiteDatabase;
import edu.csus.yaam.server.webapi.WebServerAPI;
import edu.csus.yaam.server.websocket.WebSocketServer;
import java.io.File;
import java.io.PrintWriter;
import lombok.extern.log4j.Log4j2;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.action.StoreTrueArgumentAction;
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
        optionalArguments.addArgument("--noconf")
                .action(new StoreTrueArgumentAction())
                .dest("noConfig")
                .type(boolean.class)
                .help("Uses default bundled config");


        // parse arguments
        Namespace namespace;
        try {
            namespace = parser.parseArgs(args);
        } catch (ArgumentParserException exception) {
            System.err.println("error: " + exception.getMessage());
            System.err.println();
            parser.printHelp(new PrintWriter(System.err, true));
            System.exit(-1);
            return;
        }


        // load YAAM server configuration and settings into memory
        YaamConfig config;
        try {
            config = YaamConfig.load(namespace);
            log.info("Loaded configuration");
        } catch (Throwable throwable) {
            log.fatal("Failed to load configuration", throwable);
            System.exit(-1);
            return;
        }

        // load SQLite database

        SQLiteDatabase databaseSQL = new SQLiteDatabase(config.sqliteFile);
        execute(databaseSQL::connect, "Failed to connect to SQLite database");
        execute(databaseSQL::initialize, "Failed to execute schema initialization statements");


        // construct WebAPI server
        log.info("Launching Spark WebAPI...");
        WebServerAPI apiServer = new WebServerAPI(config);
        execute(apiServer::launch, "Failed to launch embedded Spark WebAPI");
        log.info("Spark WebAPI launched");

        // construct WebSocket server
        log.info("Launching web socket server...");
        WebSocketServer webSocketServer = new WebSocketServer(config);
        execute(webSocketServer::launch, "Failed to launch WebSocketServer");
        log.info("Web socket server launched");


        System.out.println();
        log.info("YAAM Server running");
    }


    private static void execute(Runnable runnable, String fatalMessage) {
        try {
            runnable.run();
        } catch (Throwable throwable) {
            log.fatal(fatalMessage, throwable);
            System.exit(-1);
        }
    }
}