package edu.csus.yaam.client.gui.header;

import com.sun.javafx.binding.DoubleConstant;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import edu.csus.yaam.client.gui.YaamStage;
import edu.csus.yaam.client.gui.javafx.PathView;
import edu.csus.yaam.client.gui.scenes.SettingsScene;
import edu.csus.yaam.client.gui.scenes.UserLoginScene;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * @author Ryan R
 * @date 4/11/2018
 */
public class HeaderPane extends Pane {
    private final YaamStage stage;

    @Getter
    @Accessors(fluent = true)
    private StackPane brandName;
    private PathView pathBar;

    private HeaderNavigationAction loginAction;
    private HeaderNavigationAction userAction;

    public HeaderPane(YaamStage stage) {
        this.stage = stage;

        this.setId("header");

        this.constructBrandName();
        this.constructActionBar();
        this.constructPathBar();
    }

    // binding

    private void constructBrandName() {
        brandName = new StackPane();
        brandName.setId("brandName-container");

        // bind location
        brandName.layoutXProperty().bind(DoubleConstant.valueOf(0));
        brandName.layoutYProperty().bind(DoubleConstant.valueOf(0));
        brandName.prefHeightProperty().bind(DoubleConstant.valueOf(70));
        brandName.prefWidthProperty().bind(DoubleConstant.valueOf(200));
        brandName.setAlignment(Pos.CENTER);

        // TODO: Actually get a program logo/font setup
        Text programLogo = new Text("YAAM");
        programLogo.setId("brandName-text");

        // add as children
        brandName.getChildren().add(programLogo);
        this.getChildren().addAll(brandName);
    }

    private void constructActionBar() {
        Pane actionBar = new Pane();

        actionBar.layoutXProperty().bind(brandName.widthProperty());
        actionBar.prefWidthProperty().bind(this.widthProperty().subtract(brandName.widthProperty()));
        actionBar.prefHeightProperty().bind(brandName.heightProperty().divide(2));


        HeaderNavigationAction settingsIcon = new HeaderNavigationAction(FontAwesomeIcon.COG, "Settings");
        settingsIcon.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> stage.navigate(SettingsScene.class));
        actionBar.getChildren().add(settingsIcon);

        loginAction = new HeaderNavigationAction(FontAwesomeIcon.SIGN_IN, "Sign in");
        loginAction.layoutXProperty().bind(actionBar.widthProperty().subtract(loginAction.widthProperty()));
        loginAction.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> stage.navigate(UserLoginScene.class));

        userAction = new HeaderNavigationAction(FontAwesomeIcon.USER, "Ryan Riley");
        userAction.layoutXProperty().bind(actionBar.widthProperty().subtract(userAction.widthProperty()));
        settingsIcon.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {

        });
        actionBar.getChildren().add(loginAction);

        this.getChildren().addAll(actionBar);
    }

    private void constructPathBar() {
        Pane pathPane = new Pane();
        pathPane.getStyleClass().add("path-container");
        pathBar = new PathView();
        pathBar.layoutYProperty().bind(DoubleConstant.valueOf(-1));
        pathBar.prefWidthProperty().bind(this.widthProperty());
        Rectangle rectangle = new Rectangle(0, 1, 0, 25);
        rectangle.widthProperty().bind(pathBar.widthProperty());
        rectangle.heightProperty().bind(this.heightProperty());
        pathBar.setClip(rectangle);
        pathPane.getChildren().add(pathBar);

        // bind location
        pathPane.layoutXProperty().bind(brandName.widthProperty());
        pathPane.layoutYProperty().bind(brandName.heightProperty().divide(2));
        pathPane.prefWidthProperty().bind(this.widthProperty().subtract(brandName.widthProperty()));
        pathPane.prefHeightProperty().bind(brandName.heightProperty().divide(2));

        this.getChildren().addAll(pathPane);
    }


    public void setPath(PathView.Path... paths) {
        pathBar.setPath(paths);
    }
}