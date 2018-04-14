package edu.csus.yaam.client.gui.sidebar;

import com.jfoenix.controls.JFXRippler;
import com.sun.javafx.binding.DoubleConstant;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.csus.yaam.client.gui.scenes.YaamScene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import lombok.Getter;

/**
 * @author Ryan R
 * @date 4/12/2018
 */
public class NavigationOption extends Pane {
    @Getter
    private final Class<? extends YaamScene> yaamScene;

    public NavigationOption(FontAwesomeIcon icon, String text, Class<? extends YaamScene> yaamScene) {
        this.yaamScene = yaamScene;

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


        // create arrow container
        StackPane arrowContainer = new StackPane();
        arrowContainer.getStyleClass().add("arrow-container");
        arrowContainer.setPrefHeight(40);
        arrowContainer.prefWidthProperty().bind(this.widthProperty());
        arrowContainer.setPickOnBounds(false);


        this.getChildren().addAll(navigationRippler, arrowContainer);
    }

    public void select() {
        this.getStyleClass().add("selected");
    }

    public void unselect() {
        this.getStyleClass().remove("selected");
    }
}