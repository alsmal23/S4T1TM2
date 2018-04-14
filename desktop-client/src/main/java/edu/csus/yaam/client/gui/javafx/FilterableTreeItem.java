package edu.csus.yaam.client.gui.javafx;

import java.lang.reflect.Field;
import java.util.function.Predicate;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TreeItem;

/**
 * Obtained from JFoenix's TreeViewDemo, allowing for filterable tree items (e.g. searching through tree view)
 */
public class FilterableTreeItem<T> extends TreeItem<T> {
    private final ObservableList<TreeItem<T>> sourceList;
    private final FilteredList<TreeItem<T>> filteredList;
    private ObjectProperty<TreeItemPredicate<T>> predicate = new SimpleObjectProperty<>();

    public FilterableTreeItem(T value) {
        super(value);

        // all children
        sourceList = FXCollections.observableArrayList();

        // filtered children
        filteredList = new FilteredList<>(sourceList);
        filteredList.predicateProperty().bind(Bindings.createObjectBinding(() -> child -> {
            // Set the predicate of child items to force filtering
            if (child instanceof FilterableTreeItem) {
                FilterableTreeItem<T> filterableChild = (FilterableTreeItem<T>) child;
                filterableChild.setPredicate(this.predicate.get());
            }
            // If there is no predicate, keep this tree item
            if (predicate.get() == null) { return true; }
            // If there are children, keep this tree item
            if (child.getChildren().size() > 0) { return true; }
            // Otherwise ask the TreeItemPredicate
            return predicate.get().test(this, child.getValue());
        }, predicate));
        this.setHiddenFieldChildren(filteredList);
    }

    // rewrites children to a filtered list for dynamic search
    private void setHiddenFieldChildren(ObservableList<TreeItem<T>> list) {
        try {
            // rewrite child list
            Field childrenField = TreeItem.class.getDeclaredField("children"); //$NON-NLS-1$
            childrenField.setAccessible(true);
            childrenField.set(this, list);

            // obtain children notification updater, on text property change -> run predicates -> filters list -> notify listeners (children listener)
            Field declaredField = TreeItem.class.getDeclaredField("childrenListener"); //$NON-NLS-1$
            declaredField.setAccessible(true);
            list.addListener((ListChangeListener<? super TreeItem<T>>) declaredField.get(this));
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException("Could not set TreeItem.children", e); //$NON-NLS-1$
        }
    }


    public ObservableList<TreeItem<T>> getInternalChildren() {
        return sourceList;
    }

    public void setPredicate(TreeItemPredicate<T> predicate) {
        this.predicate.set(predicate);
    }

    public TreeItemPredicate getPredicate() {
        return predicate.get();
    }

    public ObjectProperty<TreeItemPredicate<T>> predicateProperty() {
        return predicate;
    }


    @FunctionalInterface
    public interface TreeItemPredicate<T> {
        boolean test(TreeItem<T> parent, T value);

        static <T> TreeItemPredicate<T> create(Predicate<T> predicate) {
            return (parent, value) -> predicate.test(value);
        }
    }
}