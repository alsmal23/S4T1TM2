package edu.csus.yaam.client.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeViewPath;
import com.sun.javafx.binding.DoubleConstant;
import java.util.function.Consumer;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.WindowEvent;
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
    private TreeView<String> pathTree;

    public HeaderPane(YaamStage stage) {
        this.stage = stage;

        this.setId("header");

        this.constructBrandName();
        this.constructPathBar();
    }


    // binding

    private void constructBrandName() {
        // construct brand name
        brandName = new StackPane();
        brandName.setId("brandNameContainer");
        brandName.layoutXProperty().bind(DoubleConstant.valueOf(0));
        brandName.layoutYProperty().bind(DoubleConstant.valueOf(0));
        brandName.prefHeightProperty().bind(DoubleConstant.valueOf(70));
        brandName.prefWidthProperty().bind(DoubleConstant.valueOf(200));
        brandName.setAlignment(Pos.CENTER);

        // TODO: Actually get a program logo/font setup
        Text programLogo = new Text("YAAM");
        programLogo.setId("brandNameText");

        // add as children
        brandName.getChildren().add(programLogo);
        this.getChildren().addAll(brandName);
    }

    private void constructPathBar() {
        // construct path bar
        Pane pathBarContainer = new Pane();

        pathBarContainer.setId("pathBarContainer");
        pathBarContainer.layoutXProperty().bind(brandName.widthProperty());
        pathBarContainer.layoutYProperty().bind(brandName.heightProperty().divide(2));
        pathBarContainer.prefWidthProperty().bind(this.widthProperty().subtract(brandName.widthProperty()));
        pathBarContainer.prefHeightProperty().bind(brandName.heightProperty().divide(2));

        // note: The path tree is only designed to have a recursive hierarchy where every node has 0 or 1 children
        pathTree = new TreeView<>();
        pathTree.rootProperty().addListener((observable, oldValue, newValue) -> {
            TreeItem<String> last = newValue;
            if (last != null) {
                while (!last.getChildren().isEmpty()) {
                    last = last.getChildren().get(0);
                }
                last.getChildren().addListener((ListChangeListener<? super TreeItem<String>>) this::pathSelectionUpdate);
            }
            pathTree.getSelectionModel().select(last);
        });
        JFXTreeViewPath pathBar = new JFXTreeViewPath(pathTree);
        // path bar really does not like to be resized. it's 37px tall and the destination is 35px tall - define clipping mask
        pathBar.layoutYProperty().bind(DoubleConstant.valueOf(-1));
        pathBar.prefWidthProperty().bind(pathBarContainer.widthProperty());
        Rectangle rectangle = new Rectangle(0, 1, 0, 0);
        rectangle.widthProperty().bind(pathBar.widthProperty());
        rectangle.heightProperty().bind(pathBarContainer.heightProperty());
        pathBar.setClip(rectangle);


        // clear tree
        this.updatePathBar();

        // for some reason, before the tree view has been displayed, it is highlighted; disables highlighting after stage is loaded
        stage.addEventFilter(WindowEvent.WINDOW_SHOWN, event -> walk(pathBar, node -> {
            if (node instanceof JFXButton) {
                ((JFXButton) node).setDisableVisualFocus(true);
            }
        }));

        // add as children
        pathBarContainer.getChildren().add(pathBar);
        this.getChildren().addAll(pathBarContainer);
    }

    /**
     * Handles dynamically updating the path bar
     */
    private void pathSelectionUpdate(Change<? extends TreeItem<String>> change) {
        while (change.next()) {
            // recursively listen for updates
            change.getAddedSubList().forEach(added -> added.getChildren().addListener((ListChangeListener<? super TreeItem<String>>) this::pathSelectionUpdate));

            TreeItem<String> last = change.getAddedSubList().get(0);
            if (last != null) {
                while (!last.getChildren().isEmpty()) {
                    last = last.getChildren().get(0);
                }
            }
            pathTree.getSelectionModel().select(last);
        }
    }

    /**
     * Recursively walks a JavaFX parent
     */
    private static void walk(Parent parent, Consumer<Node> consumer) {
        parent.getChildrenUnmodifiable().forEach(node -> {
            if (node instanceof Parent) {
                walk((Parent) node, consumer);
            }
            consumer.accept(node);
        });
    }


    /**
     * Updates the path bar hierarchy
     */
    public void updatePathBar(String... paths) {
        if (paths.length != 0) {
            TreeItem<String> root = new TreeItem<>(paths[0]);
            TreeItem<String> last = root;
            for (int i = 1; i < paths.length; i++) {
                TreeItem<String> stringTreeItem = new TreeItem<>(paths[i]);
                last.getChildren().add(stringTreeItem);
                last = stringTreeItem;
            }
            pathTree.setRoot(root);
        } else {
            pathTree.setRoot(new TreeItem<>(""));
        }
    }
}