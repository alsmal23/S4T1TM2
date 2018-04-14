package edu.csus.yaam.client.gui.scenes;

import edu.csus.yaam.client.gui.YaamStage;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import lombok.Getter;

/**
 * @author Ryan R
 * @date 4/13/2018
 */
public class ReportsScene implements YaamScene {
    private final YaamStage stage;

    @Getter
    private final StackPane scene;

    public ReportsScene(YaamStage stage) {
        this.stage = stage;

        scene = new StackPane();
        scene.setAlignment(Pos.CENTER);

        scene.getChildren().add(new Text("ReportsScene"));
    }
}