package edu.csus.yaam.client.gui.header;

import com.jfoenix.controls.JFXRippler;
import com.sun.javafx.binding.DoubleConstant;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * @author Ryan R
 * @date 4/12/2018
 */
public class HeaderNavigationAction extends Pane {
    public HeaderNavigationAction(FontAwesomeIcon icon, String text) {
        this.getStyleClass().add("navigation-option-container");

        // create holder pane
        Pane navPane = new Pane();
        navPane.getStyleClass().add("navigation-option");

        // add icon
        StackPane iconPane = new StackPane(new FontAwesomeIconView(icon));
        iconPane.setAlignment(Pos.CENTER);
        iconPane.layoutYProperty().bind(navPane.heightProperty().subtract(iconPane.heightProperty()).divide(2).add(1));
        iconPane.layoutXProperty().bind(DoubleConstant.valueOf(8));

        // add text
        StackPane textPane = new StackPane(new Text(text));
        textPane.setAlignment(Pos.CENTER);
        textPane.layoutXProperty().bind(iconPane.layoutXProperty().add(iconPane.widthProperty()).add(5));
        textPane.layoutYProperty().bind(navPane.heightProperty().subtract(textPane.heightProperty()).divide(2));

        // add to pane
        navPane.getChildren().addAll(iconPane, textPane);
        navPane.prefHeightProperty().bind(DoubleConstant.valueOf(35));
        navPane.prefWidthProperty().bind(textPane.layoutXProperty().add(textPane.widthProperty()).add(iconPane.layoutXProperty()));

        // wrap pane into a rippler
        JFXRippler rippler = new JFXRippler(navPane);
        rippler.prefWidthProperty().bind(navPane.prefWidthProperty());

        this.prefWidthProperty().bind(navPane.prefWidthProperty());
        this.prefHeightProperty().bind(DoubleConstant.valueOf(35));
        this.getChildren().add(rippler);
    }
}