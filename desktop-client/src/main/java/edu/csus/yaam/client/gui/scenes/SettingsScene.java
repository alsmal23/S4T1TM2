package edu.csus.yaam.client.gui.scenes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.binding.DoubleConstant;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import edu.csus.yaam.client.gui.YaamStage;
import edu.csus.yaam.client.gui.javafx.PathView.Path;
import javafx.geometry.Pos;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import lombok.Getter;

/**
 * @author Ryan R
 * @date 4/12/2018
 */
public class SettingsScene implements YaamScene {
    private final YaamStage stage;

    @Getter
    private final Pane scene = new Pane();

    public SettingsScene(YaamStage stage) {
        this.stage = stage;
        scene.setId("settings");

        this.initialize();
    }

    private void initialize() {
        // tab header
        JFXTabPane tabPane = new JFXTabPane();
        tabPane.layoutXProperty().bind(DoubleConstant.valueOf(15));
        tabPane.layoutYProperty().bind(DoubleConstant.valueOf(15));
        tabPane.prefWidthProperty().bind(stage.content().widthProperty().subtract(tabPane.layoutXProperty().multiply(2)));
        tabPane.prefHeightProperty().bind(stage.content().heightProperty().subtract(tabPane.layoutYProperty().multiply(2)));

        Tab serverTab = new Tab("Server");
        Pane serverPane = new Pane();
        serverTab.setContent(serverPane);

        serverPane.prefWidthProperty().bind(tabPane.widthProperty());
        serverPane.prefHeightProperty().bind(tabPane.heightProperty());

        // header
        StackPane urlLabel = new StackPane(new Text("Remote Server"));
        urlLabel.setAlignment(Pos.CENTER);
        urlLabel.getStyleClass().add("urlLabel");
        urlLabel.layoutYProperty().bind(DoubleConstant.valueOf(10));
        //        urlLabel.layoutXProperty().bind(serverPane.widthProperty().multiply(1.0 / 3).subtract(urlLabel.widthProperty().divide(2)));
        serverPane.getChildren().add(urlLabel);

        Separator separator = new Separator();
        separator.layoutYProperty().bind(urlLabel.layoutYProperty().add(urlLabel.heightProperty()).add(10));
        separator.layoutXProperty().bind(DoubleConstant.valueOf(0));
        separator.prefWidthProperty().bind(serverPane.widthProperty());
        serverPane.getChildren().add(separator);


        // settings
        StackPane label = new StackPane(new Text("Server URL:"));
        label.getStyleClass().add("side-label");
        label.setAlignment(Pos.CENTER);
        label.layoutXProperty().bind(label.widthProperty().multiply(-1).add(125));
        serverPane.getChildren().add(label);

        JFXTextField field = new JFXTextField();
        field.setPromptText("http://server-domain.tld");
        field.layoutYProperty().bind(separator.layoutYProperty().add(12));
        field.layoutXProperty().bind(urlLabel.layoutXProperty());
        serverPane.getChildren().add(field);
        field.prefWidthProperty().bind(serverPane.widthProperty().subtract(field.layoutXProperty().add(label.layoutXProperty())));
        label.layoutYProperty().bind(field.layoutYProperty().add(field.heightProperty().divide(2)).subtract(label.heightProperty().divide(2)));

        urlLabel.layoutXProperty().bind(label.layoutXProperty().add(label.widthProperty()).add(10));

        // button area
        Pane submitPane = new Pane();
        submitPane.setId("button-area");
        submitPane.prefWidthProperty().bind(serverPane.widthProperty());
        submitPane.setPrefHeight(50);
        submitPane.layoutYProperty().bind(serverPane.heightProperty().subtract(submitPane.heightProperty()));
        submitPane.layoutXProperty().bind(DoubleConstant.valueOf(0));
        serverPane.getChildren().add(submitPane);

        JFXButton saveButton = new JFXButton("Save");
        saveButton.setId("save-button");
        saveButton.setButtonType(JFXButton.ButtonType.RAISED);
        saveButton.layoutXProperty().bind(label.widthProperty());
        saveButton.layoutYProperty().bind(submitPane.heightProperty().subtract(saveButton.heightProperty()).divide(2));
        submitPane.getChildren().add(saveButton);


        tabPane.getTabs().add(serverTab);
        scene.getChildren().add(tabPane);
    }

    @Override
    public void show() {
        stage.setPath(Path.of("Settings", FontAwesomeIcon.COG));
    }
}