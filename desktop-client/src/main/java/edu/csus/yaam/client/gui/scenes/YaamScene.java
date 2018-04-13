package edu.csus.yaam.client.gui.scenes;

import javafx.scene.layout.Region;

/**
 * Marks class as a navigable scene
 *
 * @author Ryan R
 * @date 4/12/2018
 */
public interface YaamScene {
    /**
     * Retrieves a region to render as the inner content of the UI
     */
    Region getScene();

    /**
     * Executes the implemented method upon navigating to the scene
     */
    void show();
}