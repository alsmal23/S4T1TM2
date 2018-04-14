package edu.csus.yaam.client.gui.scenes;

import edu.csus.yaam.client.gui.YaamStage;
import javafx.scene.layout.Pane;
import lombok.Getter;

/**
 * @author Ryan R
 * @date 4/12/2018
 */
public class DashboardScene implements YaamScene {
    private final YaamStage stage;
    @Getter
    private final Pane scene = new Pane();

    public DashboardScene(YaamStage stage) {
        this.stage = stage;

    }

    @Override
    public void show() {
    }
}