package edu.csus.yaam.client.gui.sidebar;

import com.jfoenix.controls.JFXRippler;
import com.sun.javafx.binding.DoubleConstant;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.csus.yaam.client.gui.YaamStage;
import edu.csus.yaam.client.gui.scenes.DashboardScene;
import edu.csus.yaam.client.gui.scenes.ProjectListScene;
import edu.csus.yaam.client.gui.scenes.ReportsScene;
import edu.csus.yaam.client.gui.scenes.YaamScene;
import javafx.beans.value.ObservableNumberValue;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * @author Ryan R
 * @date 4/11/2018
 */
public class SidebarPane extends Pane {
    private final YaamStage stage;

    private SidebarNavigationOption[] navigationOptions;
    private FontAwesomeIconView collapseArrow;

    public SidebarPane(YaamStage stage) {
        this.stage = stage;

        this.setId("sidebar");
        this.initialize();
    }

    private void initialize() {
        navigationOptions = new SidebarNavigationOption[] {
                new SidebarNavigationOption(FontAwesomeIcon.HOME, "Dashboard", DashboardScene.class),
                new SidebarNavigationOption(FontAwesomeIcon.BOOK, "Projects", ProjectListScene.class),
                new SidebarNavigationOption(FontAwesomeIcon.BAR_CHART, "Reports", ReportsScene.class)
        };

        // navigation options
        ObservableNumberValue yLayout = DoubleConstant.valueOf(0);
        for (SidebarNavigationOption option : navigationOptions) {
            // define layoutY relative to previous value
            option.layoutYProperty().bind(yLayout);
            yLayout = option.layoutYProperty().add(option.heightProperty());

            option.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> stage.navigate(option.getYaamScene()));

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
        footerPane.getStyleClass().add("footer-rippler");
        footerRippler.layoutYProperty().bind(this.heightProperty().subtract(footerPane.heightProperty()));

        this.getChildren().add(footerRippler);
    }


    // enabling

    public void enable() {
        this.getStyleClass().remove("disabled");
        this.getChildren().forEach(node -> {
            if (node instanceof JFXRippler) {
                node.setDisable(false);
            }
        });
    }

    public void disable() {
        this.getStyleClass().add("disabled");
        this.getChildren().forEach(node -> {
            if (node instanceof JFXRippler) {
                node.setDisable(true);
            }
        });
    }

    // selection

    public void select(Class<? extends YaamScene> sceneType) {
        this.unselectOptions();
        for (SidebarNavigationOption navigationOption : navigationOptions) {
            if (navigationOption.getYaamScene() == sceneType) {
                navigationOption.select();
            }
        }
    }

    public void unselectOptions() {
        for (SidebarNavigationOption navigationOption : navigationOptions) {
            navigationOption.unselect();
        }
    }
}