package edu.csus.yaam.client.demo.legacy.sidebar;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeView;
import com.sun.javafx.binding.DoubleConstant;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import edu.csus.yaam.client.demo.legacy.nav.NavAction;
import edu.csus.yaam.client.demo.legacy.nav.ProjectScene;
import edu.csus.yaam.client.util.FilterableTreeItem;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * @author Ryan R
 * @date 3/27/2018
 */
@NavAction(name = "Project", icon = FontAwesomeIcon.BOOK)
public class ProjectsScene implements ProjectScene {
    @Override
    public Pane getScene() {
        Pane scene = new StackPane();
        scene.setOpaqueInsets(new Insets(10));

        Pane taskNav = new Pane(); //tasks
        taskNav.prefHeightProperty().bind(scene.heightProperty());
        taskNav.prefWidthProperty().bind(DoubleConstant.valueOf(150));
//        taskNav.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        scene.getChildren().add(taskNav);


        TextField filterField = new JFXTextField(); //search
        filterField.layoutXProperty().bind(DoubleConstant.valueOf(0));
        filterField.layoutYProperty().bind(DoubleConstant.valueOf(0));
        filterField.prefWidthProperty().bind(DoubleConstant.valueOf(200));
        filterField.setPromptText("Search");
        taskNav.getChildren().add(filterField);

        FilterableTreeItem<String> rootNode = new FilterableTreeItem<>("SAFsaf");
        JFXTreeView<String> treeView = new JFXTreeView<>(rootNode);
        treeView.setEditable(true);
        treeView.setShowRoot(false);
        rootNode.getInternalChildren().add(new FilterableTreeItem<String>("Sprint 1") {{
            this.getInternalChildren().add(new FilterableTreeItem<String>("Task A"){{
                this.getInternalChildren().add(new FilterableTreeItem<String>("Sub-Task A"){{

                }});
                this.getInternalChildren().add(new FilterableTreeItem<String>("Sub-Task B"){{

                }});
            }});
            this.getInternalChildren().add(new FilterableTreeItem<String>("Task B"){{
                this.getInternalChildren().add(new FilterableTreeItem<String>("Sub-Task A"){{

                }});
                this.getInternalChildren().add(new FilterableTreeItem<String>("Sub-Task B"){{

                }});
            }});
        }});
        rootNode.getInternalChildren().add(new FilterableTreeItem<String>("Sprint 2") {{
            this.getInternalChildren().add(new FilterableTreeItem<String>("Task A"){{
                this.getInternalChildren().add(new FilterableTreeItem<String>("Sub-Task A"){{

                }});
                this.getInternalChildren().add(new FilterableTreeItem<String>("Sub-Task B"){{

                }});
            }});
            this.getInternalChildren().add(new FilterableTreeItem<String>("Task B"){{
                this.getInternalChildren().add(new FilterableTreeItem<String>("Sub-Task A"){{

                }});
                this.getInternalChildren().add(new FilterableTreeItem<String>("Sub-Task B"){{

                }});
            }});
        }});
        rootNode.getInternalChildren().add(new FilterableTreeItem<String>("Sprint 2") {{
            this.getInternalChildren().add(new FilterableTreeItem<String>("Task A"){{
                this.getInternalChildren().add(new FilterableTreeItem<String>("Sub-Task A"){{

                }});
                this.getInternalChildren().add(new FilterableTreeItem<String>("Sub-Task B"){{

                }});
            }});
            this.getInternalChildren().add(new FilterableTreeItem<String>("Task B"){{
                this.getInternalChildren().add(new FilterableTreeItem<String>("Sub-Task A"){{

                }});
                this.getInternalChildren().add(new FilterableTreeItem<String>("Sub-Task B"){{

                }});
            }});
        }});

//        treeView.prefWidthProperty().bind(DoubleConstant.valueOf(200));
        treeView.layoutYProperty().bind(filterField.layoutYProperty().add(filterField.heightProperty()));
        treeView.layoutXProperty().bind(DoubleConstant.valueOf(0));
        treeView.prefWidthProperty().bind(DoubleConstant.valueOf(200));
        treeView.prefHeightProperty().bind(taskNav.heightProperty().subtract(filterField.heightProperty()));
        taskNav.getChildren().add(treeView);


        // define search predicate
        rootNode.predicateProperty().bind(Bindings.createObjectBinding(() -> { // predicate is reevaluated when filterfield is modified
            if (filterField.getText() == null || filterField.getText().isEmpty()) { return null; }
            return FilterableTreeItem.TreeItemPredicate.create(actor -> actor.toLowerCase().contains(filterField.getText().toLowerCase()));
        }, filterField.textProperty()));

        return scene;
    }
}