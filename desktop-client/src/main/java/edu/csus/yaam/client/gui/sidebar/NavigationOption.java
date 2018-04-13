package edu.csus.yaam.client.gui.sidebar;

import com.jfoenix.controls.JFXRippler;
import com.sun.javafx.binding.DoubleConstant;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import lombok.NonNull;

/**
 * @author Ryan R
 * @date 4/12/2018
 */
public class NavigationOption extends Pane {
    public NavigationOption(@NonNull FontAwesomeIcon icon, @NonNull String text) {
        this.getStyleClass().add("navigation-option");

        // create container
        Pane textContainer = new Pane();

        Pane iconPane = new StackPane(new FontAwesomeIconView(icon));
        iconPane.layoutYProperty().bind(textContainer.heightProperty().subtract(iconPane.heightProperty()).divide(2).add(1));
        iconPane.layoutXProperty().bind(DoubleConstant.valueOf(20));

        Pane label = new StackPane(new Text(text));
        label.layoutXProperty().bind(iconPane.layoutXProperty().add(iconPane.widthProperty()).add(12));
        label.layoutYProperty().bind(textContainer.heightProperty().subtract(label.heightProperty()).divide(2));

        textContainer.getChildren().addAll(iconPane, label);


        // create rippler
        JFXRippler navigationRippler = new JFXRippler(textContainer);
        navigationRippler.setPrefHeight(40);
        navigationRippler.prefWidthProperty().bind(this.widthProperty());

        this.setPrefHeight(40);
        this.parentProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                this.prefWidthProperty().bind(((Region) this.getParent()).widthProperty());
            }
        });

        this.getChildren().add(navigationRippler);
    }
}