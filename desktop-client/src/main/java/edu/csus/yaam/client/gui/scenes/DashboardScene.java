package edu.csus.yaam.client.gui.scenes;

import com.jfoenix.controls.JFXTabPane;
import com.sun.javafx.binding.DoubleConstant;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import edu.csus.yaam.client.gui.YaamStage;
import edu.csus.yaam.client.gui.javafx.PathView.Path;
import javafx.scene.control.Tab;
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
        scene.setId("dashboard");

        this.initialize();
    }

    private void initialize() {
        // tab header
        JFXTabPane tabPane = new JFXTabPane();
        tabPane.layoutXProperty().bind(DoubleConstant.valueOf(15));
        tabPane.layoutYProperty().bind(DoubleConstant.valueOf(15));
        tabPane.prefWidthProperty().bind(stage.content().widthProperty().subtract(tabPane.layoutXProperty().multiply(2)));
        tabPane.prefHeightProperty().bind(stage.content().heightProperty().subtract(tabPane.layoutYProperty().multiply(2)));

        Tab serverTab = new Tab("Dashboard");
        Pane serverPane = new Pane();
        serverTab.setContent(serverPane);

        serverPane.prefWidthProperty().bind(tabPane.widthProperty());
        serverPane.prefHeightProperty().bind(tabPane.heightProperty());

        tabPane.getTabs().add(serverTab);
        scene.getChildren().add(tabPane);
    }

    @Override
    public void show() {
        stage.setPath(Path.of("Dashboard", FontAwesomeIcon.HOME));
    }
}