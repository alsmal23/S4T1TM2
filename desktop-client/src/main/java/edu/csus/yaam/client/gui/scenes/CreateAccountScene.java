package edu.csus.yaam.client.gui.scenes;

import edu.csus.yaam.client.demo.NewAccountDemo;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.SneakyThrows;

/**
 * @author Ryan R
 * @date 4/12/2018
 */
public class CreateAccountScene implements YaamScene {
    @Getter
    private final Pane scene;

    @SneakyThrows
    public CreateAccountScene() {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        new NewAccountDemo().start(stage);
        stage.hide();
        scene = (Pane) stage.getScene().getRoot();
    }
}