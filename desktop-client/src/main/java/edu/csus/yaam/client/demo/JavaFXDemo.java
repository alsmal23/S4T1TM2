package edu.csus.yaam.client.demo;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.binding.StringConstant;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * A small (and definitely not exhaustive) demonstration of JavaFX
 *
 * @author Ryan R
 * @date 3/30/2018
 */
public class JavaFXDemo extends Application {
    /**
     * Typically (or at least, by convention), Java applications that apply JavaFX Stages (Windows) extend the JavaFX Application class
     * JEP 153 (http://openjdk.java.net/jeps/153) allows Java to launch JavaFX Applications without a declared main method (although, must be specified in the manifest)
     *
     * However, a main method with additional procedures may be defined. Implicitly, the default main method for a JavaFX application follows below:
     */
    /*
    public static void main(String[] args) {
        Application.launch(JavaFXDemo.class);
    }
    */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // primaryStage is the default stage JavaFX builds for you
        // Exception is part of the Application#start(Stage) signature, but can be removed if no exceptions are thrown in your procedure

        // define attributes for the primary stage
        primaryStage.setTitle("Default Title");
        // alternatively, you may set the scene dimensions in the constructor, and the Stage will inherit from those
        primaryStage.setHeight(600);
        primaryStage.setWidth(800);

        // Pane is one of the simplest forms of containers, there exist others for different purposes (StackPane, ScrollPane, etc...)
        // Since rootPane will be set as the root pane, it will automatically inherit width/height from the scene (which may inherit from the primary stage)
        Pane rootPane = new Pane();

        // Creates a text input field, using the JFoenix UI extension. The standard JavaFX one is TextField
        JFXTextField field = new JFXTextField();
        field.setPromptText("Placeholder value...");

        // Most JavaFX elements extend from one of the parent types - javafx.scene.Node
        // Nodes have observable and bindable values, and elements that will inherit and possibly add more properties

        // Some properties are readonly, and some are read and writeable
        // Properties have a datatype
        // for example, we can bind the title of the window to the value of the text field, which will overwrite the currently defined title "Default Title" immediately
        primaryStage.titleProperty().bind(field.textProperty());

        // Mathematical calculations can be done with observable values, such as subtraction
        // If any value updates that is being depended on, it will update

        // Center the text field to the 3/4th the width, and 1/2 the height
        // field.x = rootPane.width * 3/4 - field.width * 1/2
        field.layoutXProperty().bind(rootPane.widthProperty().multiply(.75f).subtract(field.widthProperty().divide(2)));
        // field.x = rootPane.width * 1/4 - field.width * 1/2
        field.layoutYProperty().bind(rootPane.heightProperty().multiply(.5).subtract(field.heightProperty().divide(2)));

        // part of JFoenix's material design
        field.setFocusColor(Color.GREEN); // when the textbox is focused
        field.setUnFocusColor(Color.RED); // when it is not focused

        // define a background (very cumbersome in java, easier in css)
        // field.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null )));
        // so why not just use inline CSS?
        field.setStyle("-fx-background-color: gray;");

        // we need to add field to the rootPane as a child, Nodes can be added to anything that is a `Parent`, which a Pane is
        rootPane.getChildren().add(field);


        // lets try a button
        JFXButton button = new JFXButton("Random Text"); // text inside button
        button.setText("Defined"); // or just define it later
        button.textProperty().bind(StringConstant.valueOf("A constant")); // or bind it to a constant value?!
        // more JFoenix UI control, let's make the button more defined
        button.setButtonType(JFXButton.ButtonType.RAISED);
        button.setBackground(new Background(new BackgroundFill(Color.rgb(97, 118, 207), null, null)));
        // There's pretty much a infinite amount of ways you can do anything in JavaFX
        // lets create a event handler
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // event gives you things you may be interested in
                System.out.println("Clicked! Is shift down: " + event.isShiftDown());
            }
        });
        // boilerplate is bothersome, lets try Java 8's concise lambdas
        button.setOnMouseClicked(event -> System.out.println("Clicked! Is shift down: " + event.isShiftDown()));
        // again, add it
        // no layoutX or layoutY was specified, it will just be by default placed in the upper left corner (aka the origin)
        rootPane.getChildren().add(button);


        // all primary stages need a Scene for the window
        Scene scene = new Scene(rootPane);
        // additional content can be defined here for a scene, like background color
        scene.setFill(Color.GRAY);
        // set the scene
        primaryStage.setScene(scene);
        // Show the stage!
        primaryStage.show();

        // By default, closing all JavaFX Stages will implicitly exit the JavaFX platform, making it cumbersome to create a new stage
        // For applications that get minimized to a tray icon, that is not desirable.
        // That can be disabled by executing the following:
        // Platform.setImplicitExit(false);
    }
}