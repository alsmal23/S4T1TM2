package edu.csus.yaam.client.demo;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.binding.StringConstant;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.*;

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
        // Note from Lindsay: I'm not sure what that Application#start(Stage) is, or it's signature.


        // ----------- STAGE -------------

        // define attributes for the primary stage
        primaryStage.setTitle("Default Title");
        primaryStage.setHeight(600);
        primaryStage.setWidth(800);
                                    //Note, could scene dimensions in the constructor, Stage will inherit from those


        // ----------- PANE -------------

        // Define the pane
        Pane rootPane = new Pane(); //simple pane container, others for different purposes (StackPane, ScrollPane, etc...)
                                    //rootPane will be set as the root pane,
                                    //it will automatically inherit width/height from the scene (which may inherit from the primary stage)

        // ----------- TASK OBJECTS MOCK UP -------------
        int taskDataLeading = 35;
        int taskHeaderSize = 24;
        int taskDataSize = 16;
        int panePaddingLeft = 15;
        int taskDataFieldsIndent = 150;


        //                  --------data labels------ These seem really repetitive, is there a simplification I can make?
        Text taskNameLabel = new Text(panePaddingLeft, 30, "Name:");
        taskNameLabel.setFont(new Font(taskHeaderSize));
        rootPane.getChildren().add(taskNameLabel);

        Text taskPathLabel = new Text(panePaddingLeft, taskNameLabel.getY()+taskDataLeading, "Path:");
        taskPathLabel.setFont(new Font(taskDataSize));
        rootPane.getChildren().add(taskPathLabel);

        Text taskDescriptionLabel = new Text(panePaddingLeft, taskPathLabel.getY()+taskDataLeading, "Description:");
        taskDescriptionLabel.setFont(new Font(taskDataSize));
        rootPane.getChildren().add(taskDescriptionLabel);

        Text taskSizeLabel = new Text(panePaddingLeft, taskDescriptionLabel.getY()+taskDataLeading, "Size:");
        taskSizeLabel.setFont(new Font(taskDataSize));
        rootPane.getChildren().add(taskSizeLabel);

        Text taskDurationLabel = new Text(panePaddingLeft, taskSizeLabel.getY()+taskDataLeading, "Duration:");
        taskDurationLabel.setFont(new Font(taskDataSize));
        rootPane.getChildren().add(taskDurationLabel);

        Text taskWorkSessionsLabel = new Text(panePaddingLeft, taskDurationLabel.getY()+taskDataLeading, "Work Sessions:");
        taskWorkSessionsLabel.setFont(new Font(taskDataSize));
        rootPane.getChildren().add(taskWorkSessionsLabel);

        //                  --------data fields------
        Text taskHeaderData = new Text(taskDataFieldsIndent, 30, "The name of the task");
        taskHeaderData.setFont(new Font(taskHeaderSize));
        rootPane.getChildren().add(taskHeaderData);

        Text taskPathData = new Text(taskDataFieldsIndent, taskHeaderData.getY()+taskDataLeading, "Project 1 > Sprint 7");
        taskPathData.setFont(new Font(taskDataSize));
        rootPane.getChildren().add(taskPathData);

        Text taskDescriptionData = new Text(taskDataFieldsIndent, taskPathData.getY()+taskDataLeading, "This task is really great. It's one of the best tasks out there. Getting this task done unblocks about 1000 other tasks and contributes to about 905 of the requirement statements. It's really the best task.");
        taskDescriptionData.setWrappingWidth(600.0); //This should be more dynamic, not sure how to do that yet.
        taskDescriptionData.setFont(new Font(taskDataSize));
        rootPane.getChildren().add(taskDescriptionData);

        Text taskSizeData = new Text(taskDataFieldsIndent, taskDescriptionData.getY()+taskDataLeading, "Small"); //This objects position (and it's label) should be place based on the END of the descriptions wrap position.
        taskSizeData.setFont(new Font(taskDataSize));
        rootPane.getChildren().add(taskSizeData);

        Text taskDurationData = new Text(taskDataFieldsIndent, taskSizeData.getY()+taskDataLeading, "7 hours, 54 minutes, and 13 seconds");
        taskDurationData.setFont(new Font(taskDataSize));
        rootPane.getChildren().add(taskDurationData);

        Text taskWorkSession01 = new Text(taskDataFieldsIndent, taskDurationData.getY()+(taskDataLeading), "11/2/17\t10:32:53 AM to 11/2/17 12:15:23 PM for a time of 1:42:30");
        taskWorkSession01.setFont(new Font(taskDataSize));
        rootPane.getChildren().add(taskWorkSession01);

        Text taskWorkSession02 = new Text(taskDataFieldsIndent, taskWorkSession01.getY()+(taskDataLeading), "11/3/17\t9:07:38 AM to 11/3/17 11:35:17 AM for a time of 2:27:39");
        taskWorkSession02.setFont(new Font(taskDataSize));
        rootPane.getChildren().add(taskWorkSession02);

        Text taskWorkSession03 = new Text(taskDataFieldsIndent, taskWorkSession02.getY()+(taskDataLeading), "11/3/17\t2:43:42 PM to 11/3/17 4:06:36 PM for a time of 1:22:54");
        taskWorkSession03.setFont(new Font(taskDataSize));
        rootPane.getChildren().add(taskWorkSession03);

        Text taskWorkSession04 = new Text(taskDataFieldsIndent, taskWorkSession03.getY()+(taskDataLeading), "11/4/17\t9:56:18 AM to 11/4/17 12:17:28 PM for a time of 2:21:10");
        taskWorkSession04.setFont(new Font(taskDataSize));
        rootPane.getChildren().add(taskWorkSession04);






        ChoiceBox taskSizeDropdown = new ChoiceBox(); // this is for editing, but I didn't want to forget how to make the element.

        //Edit button
        JFXButton editButton = new JFXButton("Edit");
        editButton.layoutXProperty().bind(rootPane.widthProperty().multiply(.9f));
        editButton.layoutYProperty().bind(rootPane.heightProperty().multiply(.01f));
        rootPane.getChildren().add(editButton);
        // ----------- Text Field -------------

        // Define text input field  // Creates a text input field, using the JFoenix UI extension. The standard JavaFX one is TextField
        JFXTextField field = new JFXTextField();
        field.setPromptText("Placeholder value...");
        primaryStage.titleProperty().bind(field.textProperty());
                                    // Most JavaFX elements extend from one of the parent types - javafx.scene.Node
                                    // Nodes have observable and bindable values, and elements that will inherit and possibly add more properties
                                    // Some properties are readonly, and some are read and writeable
                                    // Properties have a datatype
                                    // for example, we can bind the title of the window to the value of the text field, which will overwrite the currently defined title "Default Title" immediately



                                    // Mathematical calculations can be done with observable values, such as subtraction
                                    // If any value updates that is being depended on, it will update

        // Position the text field
        field.layoutXProperty().bind(rootPane.widthProperty().multiply(.9f).subtract(field.widthProperty().divide(2)));
                                     // Center the text field to the 3/4th the width, and 1/2 the height
                                     // button.x = rootPane.width * 3/4 - button.width * 1/2
        field.layoutYProperty().bind(rootPane.heightProperty().multiply(.9).subtract(field.heightProperty().divide(2)));
                                     // button.x = rootPane.width * 1/4 - button.width * 1/2

        //Underline the text filed with different colors // part of JFoenix's material design
        field.setFocusColor(Color.GREEN); // when the textbox is focused
        field.setUnFocusColor(Color.RED); // when it is not focused

        // define a background (very cumbersome in java, easier in css)
        field.setStyle("-fx-background-color: gray;"); //The css way
                                     // The java way: field.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null )));

        // Add field to rootPanewe need to add field to the rootPane as a child, Nodes can be added to anything that is a `Parent`, which a Pane is
        rootPane.getChildren().add(field);


        // ----------- Button -------------

        // lets try a button
        JFXButton button = new JFXButton("Random Text"); // text inside button
        button.setText("Defined"); // or just define it later
        button.textProperty().bind(StringConstant.valueOf("A constant")); // or bind it to a constant value?!

        // Center the button to the 1/2 the width, and 1/2 the height
        button.layoutXProperty().bind(rootPane.widthProperty().multiply(.5f).subtract(button.widthProperty().divide(2)));
                                     // field.x = rootPane.width * 3/4 - field.width * 1/2
        button.layoutYProperty().bind(rootPane.heightProperty().multiply(.9).subtract(button.heightProperty().divide(2)));
                                     // field.x = rootPane.width * 1/4 - field.width * 1/2

        // more JFoenix UI control, let's make the button more defined
        button.setButtonType(JFXButton.ButtonType.RAISED);
        button.setBackground(new Background(new BackgroundFill(Color.rgb(97, 118, 207), null, null)));

        // create an event handler
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // event gives you things you may be interested in
                System.out.println("Clicked! Is shift down: " + event.isShiftDown());
            }
        });

        // boilerplate is bothersome, lets try Java 8's concise lambdas
        button.setOnMouseClicked(event -> System.out.println("Clicked! Is shift down: " + event.isShiftDown()));

        // again, add it // no layoutX or layoutY was specified, it will just be by default placed in the upper left corner (aka the origin)
        rootPane.getChildren().add(button);

        // ----------- SCENE / STAGE -------------

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