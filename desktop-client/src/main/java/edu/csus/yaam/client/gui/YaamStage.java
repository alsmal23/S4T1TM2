package edu.csus.yaam.client.gui;

import com.sun.javafx.binding.DoubleConstant;
import edu.csus.yaam.client.YaamClient;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.experimental.Delegate;

/**
 * @author Ryan R
 * @date 4/10/2018
 */
public class YaamStage extends Stage {
    private final YaamClient client;

    // root content pane
    private final Pane content = new Pane();

    @Delegate(types = Delegates.class)
    private HeaderPane header;
    private SidebarPane sidebar;
    private Pane innerContent;

    public YaamStage(YaamClient client) {
        this.client = client;
    }

    public void launch() {
        // stage
        this.setTitle("YAAM");
        this.setMinHeight(600);
        this.setMinWidth(900);
        this.setHeight(600);
        this.setWidth(900);


        // header
        header = new HeaderPane(this);
        header.layoutXProperty().bind(DoubleConstant.valueOf(0));
        header.layoutYProperty().bind(DoubleConstant.valueOf(0));
        header.prefWidthProperty().bind(content.widthProperty());
        header.prefHeightProperty().bind(DoubleConstant.valueOf(70));

        sidebar = new SidebarPane(this);
        sidebar.layoutXProperty().bind(DoubleConstant.valueOf(0));
        sidebar.layoutYProperty().bind(header.heightProperty());
        sidebar.prefWidthProperty().bind(header.brandName().widthProperty());
        sidebar.prefHeightProperty().bind(content.heightProperty().subtract(header.heightProperty()));


        innerContent = new Pane();
        innerContent.setId("innerContent");
        innerContent.layoutXProperty().bind(sidebar.widthProperty());
        innerContent.layoutYProperty().bind(header.heightProperty());
        innerContent.prefWidthProperty().bind(this.widthProperty().subtract(sidebar.widthProperty()));
        innerContent.prefHeightProperty().bind(content.heightProperty().subtract(header.heightProperty()));


        // add children elements
        content.getChildren().addAll(header, sidebar, innerContent);


        // create scene
        Scene scene = new Scene(content);
        scene.getStylesheets().add("/ui/css/ui.css");
        this.setScene(scene);

        // display window
        this.show();
    }

    public void exit() {

    }

    private interface Delegates {
        void updatePathBar(String... paths);
    }
}