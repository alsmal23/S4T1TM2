package edu.csus.yaam.client.gui;

import com.sun.javafx.binding.DoubleConstant;
import edu.csus.yaam.client.YaamClient;
import edu.csus.yaam.client.gui.scenes.CreateAccountScene;
import edu.csus.yaam.client.gui.scenes.ProjectListScene;
import edu.csus.yaam.client.gui.scenes.PursuitExplorerScene;
import edu.csus.yaam.client.gui.scenes.UserLoginScene;
import edu.csus.yaam.client.gui.scenes.UserProfileScene;
import edu.csus.yaam.client.gui.scenes.YaamScene;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import lombok.NonNull;

/**
 * @author Ryan R
 * @date 4/10/2018
 */
public class YaamStage extends Stage {
    private final YaamClient client;

    // root content pane
    private final Pane rootPane = new Pane();
    private HeaderPane header;
    private SidebarPane sidebar;
    private Pane content;

    // indexing navigation by classes is effectively just a instanced version of 'static';
    // not ideal, however this project is too small to complain enough
    private final Map<Class<? extends YaamScene>, YaamScene> scenes = new HashMap<>();

    public YaamStage(YaamClient client) {
        this.client = client;

        // build the stage
        this.construct();

        // build inner scenes
        for (YaamScene scene : new YaamScene[] {
                new CreateAccountScene(),
                new ProjectListScene(),
                new PursuitExplorerScene(),
                new UserLoginScene(),
                new UserProfileScene()
        }) {
            scenes.put(scene.getClass(), scene);
        }
    }

    private void construct() {
        // stage
        this.setTitle("YAAM");
        this.setMinHeight(600);
        this.setMinWidth(900);
        this.setHeight(600);
        this.setWidth(900);

        // create scene
        Scene scene = new Scene(rootPane);
        scene.getStylesheets().add("/ui/css/ui.css");
        this.setScene(scene);


        // header
        header = new HeaderPane(this);
        header.layoutXProperty().bind(DoubleConstant.valueOf(0));
        header.layoutYProperty().bind(DoubleConstant.valueOf(0));
        header.prefWidthProperty().bind(rootPane.widthProperty());
        header.prefHeightProperty().bind(DoubleConstant.valueOf(70));

        // sidebar
        sidebar = new SidebarPane(this);
        sidebar.layoutXProperty().bind(DoubleConstant.valueOf(0));
        sidebar.layoutYProperty().bind(header.heightProperty());
        sidebar.prefWidthProperty().bind(header.brandName().widthProperty());
        sidebar.prefHeightProperty().bind(rootPane.heightProperty().subtract(header.heightProperty()));

        // inner pane content
        content = new Pane();
        content.setId("content");
        content.layoutXProperty().bind(sidebar.widthProperty());
        content.layoutYProperty().bind(header.heightProperty());
        content.prefWidthProperty().bind(this.widthProperty().subtract(sidebar.widthProperty()));
        content.prefHeightProperty().bind(rootPane.heightProperty().subtract(header.heightProperty()));


        // add children elements
        rootPane.getChildren().addAll(header, sidebar, content);
    }

    public void exit() {
        this.hide();
    }


    // inner content changing

    /**
     * Navigates to a inner content scene page
     *
     * @param sceneType a scene class implementing YaamScene
     */
    public void navigate(@NonNull Class<? extends YaamScene> sceneType) {
        if (scenes.containsKey(sceneType)) {
            YaamScene scene = scenes.get(sceneType);
            // update inner content scene
            this.setContent(scene.getScene());
            // allow scene to update
            scene.show();
        } else {
            throw new IllegalStateException("scene not registered: " + sceneType);
        }
    }

    private void setContent(Region region) {
        content.getChildren().setAll(region);

        region.prefWidthProperty().bind(content.widthProperty());
        region.prefHeightProperty().bind(content.heightProperty());
    }
}