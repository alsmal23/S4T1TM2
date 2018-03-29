package edu.csus.yaam.client.gui.sidebar;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import edu.csus.yaam.client.gui.nav.NavAction;
import edu.csus.yaam.client.gui.nav.ProjectScene;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * @author Ryan R
 * @date 3/27/2018
 */
@NavAction(name = "Dashboard", icon = FontAwesomeIcon.HOME)
public class DashboardScene implements ProjectScene {
    @Override
    public Pane getScene() {
        Pane scene = new StackPane();
        scene.setOpaqueInsets(new Insets(10));

scene.getChildren().add(new Text("Settings!"));

        return scene;
    }
}