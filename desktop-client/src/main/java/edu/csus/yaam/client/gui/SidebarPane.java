package edu.csus.yaam.client.gui;

import com.jfoenix.controls.JFXRippler;
import com.sun.javafx.binding.DoubleConstant;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * @author Ryan R
 * @date 4/11/2018
 */
public class SidebarPane extends Pane {
    private final YaamStage stage;

    public SidebarPane(YaamStage stage) {
        this.stage = stage;

        this.setId("sidebar");
        this.initialize();
    }

    private void initialize() {
        // create single navigation option (currently a demo)
        {
            Pane navigationOption = new Pane();
            navigationOption.getStyleClass().add("navigation-option");

            Pane icon = new StackPane(new FontAwesomeIconView(FontAwesomeIcon.HOME));
            icon.layoutYProperty().bind(navigationOption.heightProperty().subtract(icon.heightProperty()).divide(2));
            icon.layoutXProperty().bind(DoubleConstant.valueOf(20));

            Pane label = new StackPane(new Text("Dashboard"));
            label.layoutXProperty().bind(icon.layoutXProperty().add(icon.widthProperty()).add(12));
            label.layoutYProperty().bind(navigationOption.heightProperty().subtract(label.heightProperty()).divide(2));

            navigationOption.getChildren().addAll(icon, label);

            JFXRippler navigationRippler = new JFXRippler(navigationOption);
            navigationRippler.setPrefHeight(40);
            navigationRippler.prefWidthProperty().bind(this.widthProperty());

            this.getChildren().add(navigationRippler);
        }


        // collapsible sidebar arrow
        Pane footer = new Pane();
        footer.getStyleClass().add("footer");
        footer.setPrefHeight(40);
        footer.prefWidthProperty().bind(this.widthProperty());
        footer.layoutYProperty().bind(this.heightProperty().subtract(footer.heightProperty()));

        Pane contract = new StackPane(new FontAwesomeIconView(FontAwesomeIcon.ANGLE_LEFT));
        contract.getStyleClass().add("collapser");

        contract.layoutYProperty().bind(this.heightProperty().subtract(contract.heightProperty()));
        this.getChildren().add(footer);
    }
}