package edu.csus.yaam.client.gui.scenes;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import edu.csus.yaam.client.gui.YaamStage;
import edu.csus.yaam.client.gui.javafx.PathView.Path;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import lombok.Getter;

/**
 * @author Ryan R
 * @date 4/12/2018
 */
public class ProjectListScene implements YaamScene {
    private final YaamStage stage;

    @Getter
    private final StackPane scene;

    public ProjectListScene(YaamStage stage) {
        this.stage = stage;

        scene = new StackPane();
        scene.setAlignment(Pos.CENTER);

        scene.getChildren().add(new Text("Test"));
    }

    @Override
    public void show() {
        stage.setPath(Path.of("Projects", FontAwesomeIcon.BOOK));
    }
}