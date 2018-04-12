package edu.csus.yaam.client.gui;

import com.jfoenix.controls.JFXRippler;
import com.sun.javafx.binding.DoubleConstant;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * @author Ryan R
 * @date 4/11/2018
 */
public class SidebarPane extends Pane {
    private final YaamStage stage;
    //    private final Pane navNormal;
    //    private final Pane navSelected;
    //    private final Text pane;

    public SidebarPane(YaamStage stage) {
        this.stage = stage;

        this.setId("sidebar");

        // What happens when you try to mix CSS decoupling with only-Java JavaFX transitions? Dirty hacks
        //        Pane hackyCss = new Pane();
        //        navNormal = new Pane();
        //        navNormal.getStyleClass().add("navigation-option");
        //        navSelected = new Pane();
        //        navSelected.getStyleClass().add("navigation-option_hover");
        //        pane = new Text();

        // add children
        //        hackyCss.getChildren().addAll(navNormal, navSelected);
        //        this.getChildren().add(hackyCss);

        this.initialize();
    }

    private void initialize() {
        // create holder pane
        Pane navigationOption = new Pane();
        navigationOption.getStyleClass().add("navigation-option");

        Pane icon = new StackPane(new FontAwesomeIconView(FontAwesomeIcon.HOME));
        icon.layoutYProperty().bind(navigationOption.heightProperty().subtract(icon.heightProperty()).divide(2));
        icon.layoutXProperty().bind(DoubleConstant.valueOf(20));

        Pane label = new StackPane(new Text("Dashboard"));
        label.layoutXProperty().bind(icon.layoutXProperty().add(icon.widthProperty()).add(12));
        label.layoutYProperty().bind(navigationOption.heightProperty().subtract(label.heightProperty()).divide(2));

        navigationOption.getChildren().addAll(icon, label);

        JFXRippler navigationRippler = new JFXRippler(navigationOption);
        navigationRippler.setPrefHeight(40);
        navigationRippler.prefWidthProperty().bind(this.widthProperty());
//        navigationRippler.prefHeightProperty().bind(DoubleConstant.valueOf(40));

        this.getChildren().add(navigationRippler);


        Pane footer = new Pane();
        footer.getStyleClass().add("footer");
        footer.setPrefHeight(40);
        footer.prefWidthProperty().bind(this.widthProperty());
        footer.layoutYProperty().bind(this.heightProperty().subtract(footer.heightProperty()));

        Pane contract = new StackPane(new FontAwesomeIconView(FontAwesomeIcon.ANGLE_LEFT));
        contract.getStyleClass().add("collapser");

        contract.layoutYProperty().bind(this.heightProperty().subtract(contract.heightProperty()));
        this.getChildren().add(footer);


        // transition testing; decided it probably was not worth the time
        // pane.addEventHandler(MouseEvent.ANY, event -> {
        //     if (event.getEventType() == MouseEvent.MOUSE_ENTERED || event.getEventType() == MouseEvent.MOUSE_EXITED) {
        //         BorderStroke borderStrokeFrom = navNormal.getBorder().getStrokes().get(0);
        //         BorderStroke borderStrokeTo = navSelected.getBorder().getStrokes().get(0);
        //         Color topStrokeFrom = (Color) borderStrokeFrom.getTopStroke();
        //         Color topStrokeTo = (Color) borderStrokeTo.getTopStroke();
        //         Color bottomStrokeFrom = (Color) borderStrokeFrom.getBottomStroke();
        //         Color bottomStrokeTo = (Color) borderStrokeTo.getBottomStroke();
        //
        //         Color backgroundFrom = ((Color) navNormal.getBackground().getFills().get(0).getFill());
        //         Color backgroundTo = ((Color) navSelected.getBackground().getFills().get(0).getFill());
        //
        //         new Transition() {
        //             {
        //                 this.setCycleDuration(Duration.millis(100));
        //                 this.setInterpolator(Interpolator.EASE_OUT);
        //             }
        //
        //             @Override
        //             protected void interpolate(double frac) {
        //                 if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
        //                     frac = 1 - frac;
        //                 }
        //                 pane.setBackground(new Background(new BackgroundFill(backgroundFrom.interpolate(backgroundTo, frac), CornerRadii.EMPTY, Insets.EMPTY)));
        //                 pane.setBorder(new Border(new BorderStroke(
        //                         topStrokeFrom.interpolate(topStrokeTo, frac),
        //                         Color.TRANSPARENT,
        //                         bottomStrokeFrom.interpolate(bottomStrokeTo, frac),
        //                         Color.TRANSPARENT,
        //                         BorderStrokeStyle.SOLID,
        //                         BorderStrokeStyle.NONE,
        //                         BorderStrokeStyle.SOLID,
        //                         BorderStrokeStyle.NONE,
        //                         CornerRadii.EMPTY,
        //                         BorderStroke.THIN,
        //                         Insets.EMPTY
        //                 )));
        //             }
        //         }.play();
        //     }
        // });
    }
}