package edu.csus.yaam.client.demo.legacy;

import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTreeViewPath;
import com.sun.javafx.binding.DoubleConstant;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.csus.yaam.client.demo.legacy.action.LogOut;
import edu.csus.yaam.client.demo.legacy.action.SettingsScene;
import edu.csus.yaam.client.demo.legacy.nav.NavScene;
import edu.csus.yaam.client.demo.legacy.sidebar.DashboardScene;
import edu.csus.yaam.client.demo.legacy.sidebar.ProjectsScene;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Ryan R
 * @date 3/26/2018
 */
public class YaamDemo extends Application {
    private Pane content;
    private StackPane brandName;
    private Pane sidebar;
    private Pane actionBar;
    private JFXTreeViewPath pathBar;
    private ScrollPane innerContent;
    private TreeView<String> pathTree;

    @Override
    public void start(Stage primaryStage) throws Exception {
        content = new Pane();

        // construct all nodes
        this.constructBrandName();
        this.constructSidebar();
        this.constructActionBar();
        this.constructPathbar();
        this.constructInnerContent();

        // scene
        Scene scene = new Scene(content);
        scene.getStylesheets().add("/ui/css/ui-legacy.css");

        // stage
        primaryStage.setTitle("YAAM Demo");
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(900);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    // node construction

    private void constructBrandName() {
        brandName = new StackPane();
        content.getChildren().addAll(brandName);

        brandName.setId("brandNameContainer");
        brandName.layoutXProperty().bind(DoubleConstant.valueOf(0));
        brandName.layoutYProperty().bind(DoubleConstant.valueOf(0));
        brandName.prefHeightProperty().bind(DoubleConstant.valueOf(70));
        brandName.prefWidthProperty().bind(DoubleConstant.valueOf(200));
        brandName.setAlignment(Pos.CENTER);

        // TODO: Actually get a program logo/font setup
        Text programLogo = new Text("YAAM");
        programLogo.setId("brandNameText");
        brandName.getChildren().add(programLogo);
    }

    private void constructSidebar() {
        // sidebar
        sidebar = new Pane();
        content.getChildren().addAll(sidebar);

        sidebar.setId("sidebar");
        sidebar.layoutXProperty().bind(DoubleConstant.valueOf(0));
        sidebar.layoutYProperty().bind(brandName.heightProperty());
        sidebar.prefWidthProperty().bind(brandName.widthProperty());
        sidebar.prefHeightProperty().bind(content.heightProperty().subtract(brandName.heightProperty()));

        // projects

        ObservableValue<? extends Number> x = DoubleConstant.valueOf(0);

        for (NavScene navScene : NavScene.scenes(DashboardScene.class, ProjectsScene.class)) {
            Pane pane = new Pane();
//
            pane.getStyleClass().add("test");
//            pane.getStyleClass().addAll("selected");
            FontAwesomeIconView fontAwesomeIconView = new FontAwesomeIconView(navScene.icon());
            fontAwesomeIconView.getStyleClass().add("text");
            Pane pane1 = new StackPane(fontAwesomeIconView);

            Text test1 = new Text(navScene.name());
            test1.getStyleClass().add("text");
            Pane test = new StackPane(test1);
            pane1.layoutYProperty().bind(pane.heightProperty().subtract(pane1.heightProperty()).divide(2));
            pane1.layoutXProperty().bind(DoubleConstant.valueOf(20));
            test.layoutXProperty().bind(pane1.layoutXProperty().add(pane1.widthProperty()).add(10));
            test.layoutYProperty().bind(pane.heightProperty().subtract(test.heightProperty()).divide(2));

            pane.getChildren().addAll(pane1, test);

            JFXRippler rippler = new JFXRippler(pane);
            rippler.getStyleClass().add("test");
            rippler.setMaskType(JFXRippler.RipplerMask.RECT);
            rippler.minWidthProperty().bind(sidebar.widthProperty());
            rippler.maxWidthProperty().bind(sidebar.widthProperty());
            rippler.prefWidthProperty().bind(sidebar.widthProperty());
            rippler.prefHeightProperty().bind(DoubleConstant.valueOf(40));
            rippler.layoutYProperty().bind(x);
            x = rippler.layoutYProperty().add(rippler.heightProperty());   rippler.setOnMouseClicked(event -> {
                update(navScene.getNode());
                pathTree.setRoot(new TreeItem<>(navScene.name()));
                pathTree.getSelectionModel().select(pathTree.getRoot());

                for (Node node : sidebar.getChildren()) {
                    ((Parent) node).getChildrenUnmodifiable().get(0).getStyleClass().remove("selected");
                }
                            pane.getStyleClass().addAll("selected");
            });

            sidebar.getChildren().add(rippler);
        }
    }

    private void constructActionBar() {
        actionBar = new Pane();
        content.getChildren().addAll(actionBar);

        actionBar.setId("actionBar");
        actionBar.layoutXProperty().bind(brandName.widthProperty());
        actionBar.layoutYProperty().bind(DoubleConstant.valueOf(0));
        actionBar.prefWidthProperty().bind(content.widthProperty().subtract(brandName.widthProperty()));
        actionBar.prefHeightProperty().bind(brandName.heightProperty().divide(2));

        for (NavScene navScene : NavScene.scenes(SettingsScene.class, LogOut.class)) {
            // create holder pane
            Pane pane = new Pane();
            pane.setOnMouseClicked(event -> {
                update(navScene.getNode());
                pathTree.setRoot(new TreeItem<>(navScene.name()));
                pathTree.getSelectionModel().select(pathTree.getRoot());
            });
            //            pane.setBackground(new Background(new BackgroundFill(Color.GREEN, null,null)));
            pane.prefHeightProperty().bind(actionBar.heightProperty());
            if (navScene.rightAlign()) {
                pane.layoutXProperty().bind(actionBar.widthProperty().subtract(pane.widthProperty()).subtract(1));
            } else {
                pane.layoutXProperty().bind(DoubleConstant.valueOf(1));
            }
            //            pane.layoutYProperty().bind(actionBar.heightProperty().subtract(pane.heightProperty()).divide(2));
            pane.getStyleClass().add("action");

            // add icon
            FontAwesomeIconView fontAwesomeIconView = new FontAwesomeIconView(navScene.icon());
            fontAwesomeIconView.getStyleClass().add("text");
            Pane iconPane = new StackPane(fontAwesomeIconView);

            // add text
            Text text = new Text(navScene.name());
            text.getStyleClass().add("text");
            text.setFont(Font.font(12));
            Pane textPane = new StackPane(text);
            iconPane.layoutYProperty().bind(pane.heightProperty().subtract(iconPane.heightProperty()).divide(2).subtract(1));
            iconPane.layoutXProperty().bind(DoubleConstant.valueOf(8));
            textPane.layoutXProperty().bind(iconPane.layoutXProperty().add(iconPane.widthProperty()).add(5));
            textPane.layoutYProperty().bind(pane.heightProperty().subtract(textPane.heightProperty()).divide(2));

            // add to pane
            pane.getChildren().addAll(iconPane, textPane);
            pane.prefWidthProperty().bind(textPane.layoutXProperty().add(textPane.widthProperty()).add(8));

            // wrap pane into a rippler
            //            JFXRippler rippler = new JFXRippler(pane);
            //            rippler.minWidthProperty().bind(pane.widthProperty());
            //            rippler.maxWidthProperty().bind(pane.widthProperty());
            //            rippler.prefWidthProperty().bind(pane.widthProperty());
            //            rippler.prefHeightProperty().bind(pane.heightProperty());
            actionBar.getChildren().add(pane);
        }
    }

    private void constructPathbar() {
        StackPane pathBarContainer = new StackPane();
        content.getChildren().addAll(pathBarContainer);

        pathBarContainer.setId("pathBar");
        pathBarContainer.setAlignment(Pos.CENTER);
        pathBarContainer.layoutXProperty().bind(brandName.widthProperty());
        pathBarContainer.layoutYProperty().bind(brandName.heightProperty().divide(2));
        pathBarContainer.prefWidthProperty().bind(content.widthProperty().subtract(brandName.widthProperty()));
        pathBarContainer.prefHeightProperty().bind(brandName.heightProperty().divide(2));

        pathTree = new TreeView<>();
        pathBar = new JFXTreeViewPath(pathTree);

        pathTree.setRoot(new TreeItem<>("Home"));

        pathTree.getSelectionModel().select(pathTree.getRoot());
        pathBarContainer.getChildren().add(pathBar);
    }

    private void constructInnerContent() {
        innerContent = new ScrollPane();
        innerContent.setId("innerContent");

        innerContent.layoutXProperty().bind(sidebar.widthProperty());
        innerContent.layoutYProperty().bind(brandName.heightProperty());
        innerContent.prefWidthProperty().bind(content.widthProperty().subtract(brandName.widthProperty()));
        innerContent.prefHeightProperty().bind(content.heightProperty().subtract(brandName.heightProperty()));
        content.getChildren().add(innerContent);
    }


    public void update(Node parent) {
        innerContent.setContent(parent);
        innerContent.setFitToWidth(true);
        innerContent.setFitToHeight(true);
    }
}