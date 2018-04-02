package edu.csus.yaam.server;

import edu.csus.yaam.server.db.SQLiteDatabase;
import edu.csus.yaam.server.webapi.WebServerAPI;
import edu.csus.yaam.server.websocket.WebSocketServer;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

/**
 * @author Ryan R
 * @date 4/1/2018
 */
@Log4j2
@Accessors(fluent = true)
public class YaamServer {
    @Getter
    private final YaamConfig config;
    @Getter
    private final SQLiteDatabase database;

    @Getter
    private final WebServerAPI webServerAPI;
    @Getter
    private final WebSocketServer webSocketServer;

    public YaamServer(YaamConfig config) {
        this.config = config;
        database = new SQLiteDatabase(config.sqliteFile);
        webServerAPI = new WebServerAPI(config);
        webSocketServer = new WebSocketServer(config);
    }

    public void launch() {
        log.info("Connecting to SQL database");
        // load SQLite database
        executeFatal(database::connect, "Failed to connect to SQLite database");
        executeFatal(database::initialize, "Failed to execute schema initialization statements");
        log.info("Connected to SQL database");

        // construct WebAPI server
        log.info("Launching Spark WebAPI...");
        executeFatal(webServerAPI::launch, "Failed to launch embedded Spark WebAPI");
        log.info("Spark WebAPI launched");

        // construct WebSocket server
        log.info("Launching web socket server...");
        executeFatal(webSocketServer::launch, "Failed to launch WebSocketServer");
        log.info("Web socket server launched");
    }


    /**
     * Executes a statement with the possibility of being fatal for the process
     */
    private static void executeFatal(Runnable runnable, String fatalMessage) {
        try {
            runnable.run();
        } catch (Throwable throwable) {
            log.fatal(fatalMessage, throwable);
            System.exit(-1);
        }
    }
}