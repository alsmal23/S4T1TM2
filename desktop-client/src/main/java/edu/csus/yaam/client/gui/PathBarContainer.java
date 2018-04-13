package edu.csus.yaam.client.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeViewPath;
import com.sun.javafx.binding.DoubleConstant;
import java.util.function.Consumer;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.WindowEvent;
import lombok.Data;
import lombok.NonNull;

/**
 * @author Ryan R
 * @date 4/12/2018
 */
public class PathBarContainer extends Pane {
    // note: the path tree is only designed to have a recursive hierarchy where every node has 0 or 1 children
    private final TreeView<String> pathTree = new TreeView<>();
    private final JFXTreeViewPath pathBar = new JFXTreeViewPath(pathTree);

    private final YaamStage stage;
    private boolean enabled;
    private Path[] queuedPath;

    public PathBarContainer(YaamStage stage) {
        this.stage = stage;

        this.setId("path-container");
        this.initialize();
    }

    private void initialize() {
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
        // path bar really does not like to be resized. it's 37px tall and the destination is 35px tall - define clipping mask
        pathBar.layoutYProperty().bind(DoubleConstant.valueOf(-1));
        pathBar.prefWidthProperty().bind(this.widthProperty());
        Rectangle rectangle = new Rectangle(0, 1, 0, 0);
        rectangle.widthProperty().bind(pathBar.widthProperty());
        rectangle.heightProperty().bind(this.heightProperty());
        pathBar.setClip(rectangle);

        // clear tree
        this.setPath();

        /*
         * Before a stage has been rendered, the root node in the tree view is highlighted on the path view,
         * which may be disabled by recursively walking the JFXTreeViewPath and applying JFXButton#setDisableVisualFocus(true)
         * The current workaround is to not define the callback paths until the stage is shown, which is necessary for
         * adding listener hooks on the JFXButtons for path callbacks
         */
        stage.addEventHandler(WindowEvent.WINDOW_SHOWN, event -> {
            enabled = true;
            if (queuedPath != null) {
                this.setPath(queuedPath);
            }
        });
        stage.addEventHandler(WindowEvent.WINDOW_HIDDEN, event -> enabled = false);

        // add as children
        this.getChildren().add(pathBar);
    }


    /**
     * Handles dynamically updating the path bar
     */
    private void pathSelectionUpdate(ListChangeListener.Change<? extends TreeItem<String>> change) {
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
     * Recursively walks a JavaFX parent's children
     */
    private static void walk(Parent parent, Consumer<Node> visitor) {
        parent.getChildrenUnmodifiable().forEach(node -> {
            if (node instanceof Parent) {
                walk((Parent) node, visitor);
            }
            visitor.accept(node);
        });
    }


    /**
     * Updates the path bar with the supplied paths and callbacks
     */
    public void setPath(Path... paths) {
        if (enabled) {
            if (paths.length != 0) {
                TreeItem<String> parent = null;
                for (int i = 0; i < paths.length; i++) {
                    Path path = paths[i];
                    TreeItem<String> item = new TreeItem<>(path.path);
                    if (i == 0) {
                        pathTree.setRoot(item);
                        parent = item;
                    } else {
                        parent.getChildren().add(parent);
                    }
                }

                // DFS search will walk the JFXButton's in order, apply mouse pressed event handler
                walk(pathBar, new Consumer<Node>() {
                    private int index = 0;

                    @Override
                    public void accept(Node node) {
                        if (node instanceof JFXButton) {
                            Path path = paths[index++];

                            if (path.runnable != null) {
                                node.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> path.runnable.run());
                            }
                            ((JFXButton) node).setDisableVisualFocus(true);
                        }
                    }
                });
            } else {
                pathTree.setRoot(new TreeItem<>(""));
            }
        } else {
            queuedPath = paths;
        }
    }


    /**
     * Path bar data structure
     */
    @Data(staticConstructor = "of")
    public static class Path {
        @NonNull
        private final String path;
        private final Runnable runnable;

        public static Path of(String path) {
            return new Path(path, null);
        }
    }
}