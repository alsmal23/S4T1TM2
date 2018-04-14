package edu.csus.yaam.client.gui.scenes;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import edu.csus.yaam.client.gui.YaamStage;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import lombok.Getter;

import static edu.csus.yaam.client.gui.javafx.PathView.Path;

/**
 * @author Ryan R
 * @date 4/12/2018
 */
public class PursuitExplorerScene implements YaamScene {
    private final YaamStage stage;

    @Getter
    private final StackPane scene;

    public PursuitExplorerScene(YaamStage stage) {
        this.stage = stage;

        scene = new StackPane();
        scene.setAlignment(Pos.CENTER);

        scene.getChildren().add(new Text("PursuitExplorerScene"));
    }

    @Override
    public void show() {
        stage.setPath(
                Path.of("Projects", FontAwesomeIcon.BOOK)
                // current project path
        );
    }
}