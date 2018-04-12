package edu.csus.yaam.client.demo.legacy.action;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import edu.csus.yaam.client.demo.legacy.nav.NavAction;
import edu.csus.yaam.client.demo.legacy.nav.ProjectScene;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * @author Ryan R
 * @date 3/27/2018
 */
@NavAction(name = "Settings", icon = FontAwesomeIcon.COG)
public class SettingsScene implements ProjectScene {
    @Override
    public Pane getScene() {
        Pane scene = new StackPane();
        scene.setOpaqueInsets(new Insets(10));

        scene.getChildren().add(new Text("Settings!"));

        return scene;
    }
}