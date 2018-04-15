package edu.csus.yaam.client.gui;

import com.sun.javafx.binding.DoubleConstant;
import edu.csus.yaam.client.YaamClient;
import edu.csus.yaam.client.gui.header.HeaderPane;
import edu.csus.yaam.client.gui.javafx.PathView;
import edu.csus.yaam.client.gui.scenes.CreateAccountScene;
import edu.csus.yaam.client.gui.scenes.DashboardScene;
import edu.csus.yaam.client.gui.scenes.ProjectListScene;
import edu.csus.yaam.client.gui.scenes.PursuitExplorerScene;
import edu.csus.yaam.client.gui.scenes.ReportsScene;
import edu.csus.yaam.client.gui.scenes.SettingsScene;
import edu.csus.yaam.client.gui.scenes.UserLoginScene;
import edu.csus.yaam.client.gui.scenes.UserProfileScene;
import edu.csus.yaam.client.gui.scenes.YaamScene;
import edu.csus.yaam.client.gui.sidebar.SidebarPane;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;

/**
 * @author Ryan R
 * @date 4/10/2018
 */
@Accessors(fluent = true)
public class YaamStage extends Stage {
    private final YaamClient client;

    // root content pane
    private final Pane rootPane = new Pane();

    private HeaderPane header;
    private SidebarPane sidebar;
    @Getter
    private Pane content;

    // indexing navigation by classes is effectively just a instanced version of 'static';
    // not ideal, however this project is too small to complain enough
    private final Map<Class<? extends YaamScene>, YaamScene> scenes = new HashMap<>();

    public YaamStage(YaamClient client) {
        this.client = client;

        // build the stage
        this.initialize();

        // build inner scenes
        for (YaamScene scene : new YaamScene[] {
                new CreateAccountScene(this),
                new DashboardScene(this),
                new ProjectListScene(this),
                new PursuitExplorerScene(this),
                new ReportsScene(this),
                new SettingsScene(this),
                new UserLoginScene(this),
                new UserProfileScene()
        }) {
            scenes.put(scene.getClass(), scene);
        }
    }

    private void initialize() {
        // stage
        this.setTitle("YAAM");
        this.setMinHeight(600);
        this.setMinWidth(900);
        this.setHeight(600);
        this.setWidth(900);

        // create scene
        Scene scene = new Scene(rootPane);
        // add CSS layout
        scene.getStylesheets().addAll(
                "/ui/css/yaam.css",
                "/ui/css/no-highlight.css",
                "/ui/css/scenes/settings.css",
                "/ui/css/scenes/dashboard.css"
        );
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
        content.prefWidthProperty().bind(header.widthProperty().subtract(sidebar.widthProperty()));
        content.prefHeightProperty().bind(rootPane.heightProperty().subtract(header.heightProperty()));


        // add children elements
        rootPane.getChildren().addAll(content, header, sidebar);

        Platform.runLater(() -> {
            // default scene
            this.navigate(DashboardScene.class);
        });
    }

    public void exit() {
        this.hide();
    }


    // inner content changing

    public void setPath(PathView.Path... paths) {
        header.setPath(paths);
    }

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
            // update sidebar
            sidebar.select(sceneType);
        } else {
            throw new IllegalStateException("scene not registered: " + sceneType);
        }
    }

    private void setContent(Region region) {
        content.getChildren().setAll(region);

        region.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        region.prefWidthProperty().bind(content.widthProperty());
        region.prefHeightProperty().bind(content.heightProperty());
    }
}