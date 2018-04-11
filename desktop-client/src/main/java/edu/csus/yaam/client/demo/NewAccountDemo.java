package edu.csus.yaam.client.demo;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


/**
 * A demo, and hopefully mostly reusable, example of what our login screen could look like
 *
 * @author Lindsay Haven
 * @date 04/11/2018
 */


public class NewAccountDemo extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // ----------- STAGE -------------
        primaryStage.setTitle("New Account");
        primaryStage.setHeight(600);
        primaryStage.setWidth(800);


        // ----------- PANE -------------
        Pane rootPane = new Pane();


        //------------ BOUNDING BOX ------------ Focuses the user onto important elements of the page
        Rectangle newAccountBoundingBox = new Rectangle();
        newAccountBoundingBox.setWidth(300);
        newAccountBoundingBox.setHeight(300);
        newAccountBoundingBox.layoutXProperty().bind(rootPane.widthProperty().multiply(.5f).subtract(newAccountBoundingBox.widthProperty().divide(2)));
        newAccountBoundingBox.layoutYProperty().bind(rootPane.heightProperty().multiply(.5f).subtract(newAccountBoundingBox.heightProperty().divide(2)));
        newAccountBoundingBox.setFill(Color.SNOW);
        newAccountBoundingBox.setStroke(Color.GRAY);
        rootPane.getChildren().add(newAccountBoundingBox);


        JFXTextField newUserNameField = new JFXTextField();
        newUserNameField.setPromptText("You new username: you will login with this name");
        newUserNameField.layoutXProperty().bind(rootPane.widthProperty().multiply(.5f).subtract(newUserNameField.widthProperty().divide(2)));
        newUserNameField.layoutYProperty().bind(rootPane.heightProperty().multiply(.5f).subtract(newAccountBoundingBox.heightProperty().divide(2).subtract(30)));
        newUserNameField.setFocusColor(Color.GREEN);
        rootPane.getChildren().add(newUserNameField);

        JFXTextField newDisplayNameField = new JFXTextField();
        newDisplayNameField.setPromptText("You new display name: other users will see this name");
        newDisplayNameField.layoutXProperty().bind(rootPane.widthProperty().multiply(.5f).subtract(newDisplayNameField.widthProperty().divide(2)));
        newDisplayNameField.layoutYProperty().bind(rootPane.heightProperty().multiply(.5f).subtract(newAccountBoundingBox.heightProperty().divide(2).subtract(60)));
        newDisplayNameField.setFocusColor(Color.GREEN);
        rootPane.getChildren().add(newDisplayNameField);

        JFXPasswordField passwordFieldNew = new JFXPasswordField();
        passwordFieldNew.setPromptText("Password");
        passwordFieldNew.setFocusColor(Color.GREEN);
        passwordFieldNew.setStyle("-fx-text-box-border: Snow;");
        passwordFieldNew.layoutXProperty().bind(rootPane.widthProperty().multiply(.5f).subtract(passwordFieldNew.widthProperty().divide(2)));
        passwordFieldNew.layoutYProperty().bind(rootPane.heightProperty().multiply(.5f).subtract(newAccountBoundingBox.heightProperty().divide(2).subtract(90)));
        rootPane.getChildren().add(passwordFieldNew);

        JFXPasswordField passwordFieldConfirm = new JFXPasswordField();
        passwordFieldConfirm.setPromptText("Password");
        passwordFieldConfirm.setFocusColor(Color.GREEN);
        passwordFieldConfirm.setStyle("-fx-text-box-border: Snow;");
        passwordFieldConfirm.layoutXProperty().bind(rootPane.widthProperty().multiply(.5f).subtract(passwordFieldConfirm.widthProperty().divide(2)));
        passwordFieldConfirm.layoutYProperty().bind(rootPane.heightProperty().multiply(.5f).subtract(newAccountBoundingBox.heightProperty().divide(2).subtract(120)));
        rootPane.getChildren().add(passwordFieldConfirm);

        JFXButton createAccountButton = new JFXButton("Create Account"); //Can we make this bold?
        createAccountButton.setBackground(new Background(new BackgroundFill(Color.rgb(225, 225, 225), null, null)));
        createAccountButton.layoutXProperty().bind(rootPane.widthProperty().multiply(.5f).subtract(createAccountButton.widthProperty().divide(2)));
        createAccountButton.layoutYProperty().bind(rootPane.heightProperty().multiply(.5f).subtract(newAccountBoundingBox.heightProperty().divide(2).subtract(150)));
        rootPane.getChildren().add(createAccountButton);









        // ----------- DEFINE/SET STAGE/SCENE -------------
        Scene scene = new Scene(rootPane);
        scene.setFill(Color.LIGHTGRAY);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
