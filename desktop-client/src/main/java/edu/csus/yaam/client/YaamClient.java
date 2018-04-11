package edu.csus.yaam.client;

import edu.csus.yaam.client.gui.YaamStage;
import edu.csus.yaam.client.tray.TrayManager;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * @author Ryan R
 * @date 4/9/2018
 */
@Accessors(fluent = true)
public class YaamClient {
    // internal client
    @Getter
    private final LocalStorage localStorage = new LocalStorage();
    @Getter
    private final RemoteClientAPI clientApi = new RemoteClientAPI(this);

    // gui elements
    private final YaamStage window = new YaamStage(this);
    private final TrayManager trayManager = new TrayManager(this);


    public void initialize() {
        localStorage.load();

        // prevent JavaFX platform from exiting when YaamStage is minimized to tray icon
        Platform.setImplicitExit(false);

        trayManager.initialize();
        window.launch();
    }

    /**
     * Gracefully exits GUI
     */
    public void exit() {
        // remove existing GUI components
        window.exit();
        trayManager.exit();

        // exit JavaFX platform
        Platform.exit();

        // prevent exit hanging, wait up to 1 minute before forcefully exiting the JVM
        Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            return thread;
        }).schedule(() -> System.exit(-1), 1, TimeUnit.HOURS);
    }
}