package edu.csus.yaam.client.tray;

import edu.csus.yaam.client.YaamClient;
import java.awt.SystemTray;
import java.awt.TrayIcon;

/**
 * @author Ryan R
 * @date 4/10/2018
 */
public class TrayManager {
    private final YaamClient client;
    private TrayIcon trayIcon;

    public TrayManager(YaamClient client) {
        this.client = client;
    }


    public void initialize() {

    }

    public void exit() {
        SystemTray.getSystemTray().remove(trayIcon);
    }
}