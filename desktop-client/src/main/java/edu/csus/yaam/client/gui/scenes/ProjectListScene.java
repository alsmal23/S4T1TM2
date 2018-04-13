package edu.csus.yaam.client.gui.scenes;

import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import lombok.Getter;

/**
 * @author Ryan R
 * @date 4/12/2018
 */
public class ProjectListScene implements YaamScene {
    @Getter
    private final StackPane scene;

    public ProjectListScene() {
        scene = new StackPane();
        scene.setAlignment(Pos.CENTER);
        scene.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));

        scene.getChildren().add(new Text("Test"));
    }
}