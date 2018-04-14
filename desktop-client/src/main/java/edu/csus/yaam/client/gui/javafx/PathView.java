/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package edu.csus.yaam.client.gui.javafx;

import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.svg.SVGGlyph;
import com.jfoenix.utils.JFXNodeUtils;
import com.sun.javafx.binding.ObjectConstant;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;

/**
 * Forked from JFXTreeViewPath, generic path view
 * Far from optimal or elegant, but functionally aesthetic
 *
 * @author Shadi Shaheen
 * @version 1.0
 * @since 2017-02-11
 */
public class PathView extends ScrollPane {
    private static final String DEFAULT_STYLE_CLASS = "path-view";
    private static final double OFFSET = 10;

    private final PseudoClass firstClass = PseudoClass.getPseudoClass("first");
    private final PseudoClass nextClass = PseudoClass.getPseudoClass("next");

    private final Region clip = new Region();
    private final HBox container = new HBox();

    @Getter
    @Accessors(fluent = true)
    private final ListProperty<Path> pathProperty = new SimpleListProperty<>();

    public PathView() {
        this.getStyleClass().add(DEFAULT_STYLE_CLASS);

        this.setClip(clip);
        clip.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(3), Insets.EMPTY)));
        this.backgroundProperty().addListener(observable -> JFXNodeUtils.updateBackground(getBackground(), clip));

        container.getStyleClass().add("buttons-container");
        container.getChildren().add(new Label("Selection Path..."));
        container.setAlignment(Pos.CENTER_LEFT);
        container.widthProperty().addListener(observable -> setHvalue(getHmax()));
        this.setContent(container);
        this.setPannable(true);
        setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        setFitToHeight(true);
        JFXScrollPane.smoothHScrolling(this);

        pathProperty.addListener((ListChangeListener<Path>) change -> render());
    }

    public void setPath(@NonNull Path... path) {
        pathProperty.bind(ObjectConstant.valueOf(FXCollections.observableArrayList(path)));
    }


    private void render() {
        ObservableList<Path> paths = pathProperty.get();

        int level = 0;
        List<Node> newPath = new ArrayList<>();
        for (int i = 0; i < paths.size(); i++) {
            Path path = paths.get(i);

            Node button = createSegment(path, i == 0);
            StackPane container = new StackPane(button);
            container.setPickOnBounds(false);

            container.setTranslateX((-OFFSET - 1) * level++);
            if (i != paths.size() - 1) {
                SVGGlyph arrow = new SVGGlyph("M366 698l196-196-196-196 60-60 256 256-256 256z", Color.BLACK);
                arrow.setSizeForWidth(6);
                arrow.setMouseTransparent(true);
                StackPane.setAlignment(arrow, Pos.CENTER_RIGHT);
                container.getChildren().add(arrow);
            }
            newPath.add(container);
        }
        container.getChildren().setAll(newPath);
    }


    private JFXRippler createSegment(Path path, boolean first) {
        StackPane pane2 = new StackPane(new Text(path.path));
        pane2.setAlignment(Pos.CENTER);
        pane2.setPadding(new Insets(0, 0, 0, path.icon != null ? 4 : 1));
        HBox pane = new HBox(pane2);
        pane.setPadding(new Insets(OFFSET, 1.5 * OFFSET, OFFSET, OFFSET + (!first ? OFFSET : 0)));
        pane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        if (path.icon != null) {
            Pane innerIconPane = new Pane(new FontAwesomeIconView(path.icon));

            Pane outerIconPane = new Pane(innerIconPane);
            innerIconPane.layoutYProperty().bind(pane.heightProperty().subtract(innerIconPane.heightProperty()).divide(2).add(1));
            pane.getChildren().add(0, outerIconPane);
        }

        JFXRippler rippler = new JFXRippler(pane) {
            @Override
            protected void layoutChildren() {
                super.layoutChildren();
                double width = getWidth();
                double height = getHeight();
                if (first) {
                    setClip(new Polygon(
                            0.0, 0.0,
                            width - OFFSET, 0.0,
                            width, height / 2,
                            width - OFFSET, height,
                            0.0, height
                    ));
                } else {
                    setClip(new Polygon(
                            0.0, 0.0,
                            width - OFFSET, 0.0,
                            width, height / 2,
                            width - OFFSET, height,
                            0.0, height,
                            OFFSET, height / 2
                    ));
                }
            }
        };

        if (path.callback != null ) {
            rippler.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> path.callback.run());
        }

        return rippler;
    }


    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        clip.resizeRelocate(0, 0, getWidth(), getHeight());
    }

    @Override
    protected double computeMinHeight(double width) {
        return super.computePrefHeight(width);
    }


    /**
     * Path bar data structure
     */
    @Data(staticConstructor = "of")
    public static class Path {
        @NonNull
        private final String path;
        private final FontAwesomeIcon icon;
        private final Runnable callback;

        public static Path of(String path) {
            return new Path(path, null, null);
        }

        public static Path of(String path, FontAwesomeIcon icon) {
            return new Path(path, icon, null);
        }

        public static Path of(String path, Runnable callback) {
            return new Path(path, null, callback);
        }
    }
}