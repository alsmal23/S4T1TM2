package edu.csus.yaam.client.gui;

import com.sun.javafx.binding.DoubleConstant;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * @author Ryan R
 * @date 4/11/2018
 */
@Accessors(fluent = true)
public class HeaderPane extends Pane {
    private final YaamStage stage;

    @Getter
    private StackPane brandName;
    private PathBarContainer pathPane;

    public HeaderPane(YaamStage stage) {
        this.stage = stage;

        this.setId("header");

        this.constructBrandName();
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

    private void constructPathBar() {
        pathPane = new PathBarContainer(stage);

        // bind location
        pathPane.layoutXProperty().bind(brandName.widthProperty());
        pathPane.layoutYProperty().bind(brandName.heightProperty().divide(2));
        pathPane.prefWidthProperty().bind(this.widthProperty().subtract(brandName.widthProperty()));
        pathPane.prefHeightProperty().bind(brandName.heightProperty().divide(2));

        this.getChildren().addAll(pathPane);
    }


    public void updatePathBar(PathBarContainer.Path... paths) {
        pathPane.setPath(paths);
    }
}