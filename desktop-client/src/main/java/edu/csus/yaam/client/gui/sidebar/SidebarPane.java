package edu.csus.yaam.client.gui.sidebar;

import com.jfoenix.controls.JFXRippler;
import com.sun.javafx.binding.DoubleConstant;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.csus.yaam.client.gui.YaamStage;
import javafx.beans.value.ObservableNumberValue;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * @author Ryan R
 * @date 4/11/2018
 */
public class SidebarPane extends Pane {
    private final YaamStage stage;

    private FontAwesomeIconView collapseArrow;

    public SidebarPane(YaamStage stage) {
        this.stage = stage;

        this.setId("sidebar");
        this.initialize();
    }

    private void initialize() {
        // navigation options
        ObservableNumberValue yLayout = DoubleConstant.valueOf(0);
        for (NavigationOption option : new NavigationOption[] {
                new NavigationOption(FontAwesomeIcon.HOME, "Dashboard"),
                new NavigationOption(FontAwesomeIcon.BOOK, "Projects"),
                new NavigationOption(FontAwesomeIcon.BAR_CHART, "Reports"),
                new NavigationOption(FontAwesomeIcon.SERVER, "Servers")
        }) {
            // define layoutY relative to previous value
            option.layoutYProperty().bind(yLayout);
            yLayout = option.layoutYProperty().add(option.heightProperty());

            // add to pane
            this.getChildren().add(option);
        }


        // collapsible sidebar arrow
        StackPane footerPane = new StackPane();
        footerPane.getStyleClass().add("footer");
        footerPane.setPrefHeight(40);
        footerPane.prefWidthProperty().bind(this.widthProperty());
        footerPane.setAlignment(Pos.CENTER);
        collapseArrow = new FontAwesomeIconView(FontAwesomeIcon.ANGLE_LEFT);
        footerPane.getChildren().add(new StackPane(collapseArrow));

        JFXRippler footerRippler = new JFXRippler(footerPane);
        footerRippler.layoutYProperty().bind(this.heightProperty().subtract(footerPane.heightProperty()));

        this.getChildren().add(footerRippler);
    }
}