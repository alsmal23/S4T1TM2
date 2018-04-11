package edu.csus.yaam.client;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Ryan R
 * @date 3/27/2018
 */
public class YaamClientLauncher extends Application {
    public static void main(String[] args) {
        Application.launch(YaamClientLauncher.class);
    }

    @Override
    public void start(Stage primaryStage) {
        new YaamClient().initialize();
    }
}