package edu.csus.yaam.client.demo;


import com.jfoenix.controls.JFXPasswordField;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import com.sun.javafx.binding.StringConstant;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.text.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;


/**
 * A demo, and hopefully mostly reusable, example of what our login screen could look like
 *
 * @author Lindsay Haven
 * @date 04/10/2018
 */

public class LoginDemo extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // ----------- STAGE -------------
        primaryStage.setTitle("Login");
        primaryStage.setHeight(600);
        primaryStage.setWidth(800);


        // ----------- PANE -------------
        Pane rootPane = new Pane();


        //------------ BOUNDING BOX ------------ Focuses the user onto important elements of the page
        Rectangle loginBoundingBox = new Rectangle();
        loginBoundingBox.setWidth(250);
        loginBoundingBox.setHeight(300);
        loginBoundingBox.layoutXProperty().bind(rootPane.widthProperty().multiply(.5f).subtract(loginBoundingBox.widthProperty().divide(2)));
        loginBoundingBox.layoutYProperty().bind(rootPane.heightProperty().multiply(.5f).subtract(loginBoundingBox.heightProperty().divide(2)));

        loginBoundingBox.setFill(Color.SNOW);
        loginBoundingBox.setStroke(Color.GRAY);
        rootPane.getChildren().add(loginBoundingBox);


        // ----------- INPUTS AND BUTTONS -------------
        JFXTextField usernameField = new JFXTextField();
        usernameField.setPromptText("Username");
        usernameField.layoutXProperty().bind(rootPane.widthProperty().multiply(.5f).subtract(usernameField.widthProperty().divide(2)));
        usernameField.layoutYProperty().bind(rootPane.heightProperty().multiply(.5f).subtract(loginBoundingBox.heightProperty().divide(2).subtract(30)));
        usernameField.setFocusColor(Color.GREEN);
        rootPane.getChildren().add(usernameField);


        JFXPasswordField passwordField = new JFXPasswordField();
        passwordField.setPromptText("Password");
        passwordField.setFocusColor(Color.GREEN);
        passwordField.setStyle("-fx-text-box-border: Snow;");
        passwordField.layoutXProperty().bind(rootPane.widthProperty().multiply(.5f).subtract(passwordField.widthProperty().divide(2)));
        passwordField.layoutYProperty().bind(rootPane.heightProperty().multiply(.5f).subtract(loginBoundingBox.heightProperty().divide(2).subtract(70)));
        rootPane.getChildren().add(passwordField);


        Text loginErrorText = new Text("Account not found");
        loginErrorText.setFill(Color.SNOW);
        loginErrorText.layoutXProperty().bind(rootPane.widthProperty().multiply(.5f).subtract(passwordField.widthProperty().divide(2)));
        loginErrorText.layoutYProperty().bind(rootPane.heightProperty().multiply(.5f).subtract(loginBoundingBox.heightProperty().divide(2).subtract(120)));
        rootPane.getChildren().add(loginErrorText);


        JFXButton loginButton = new JFXButton("LOGIN"); //Can we make this bold?
        loginButton.setBackground(new Background(new BackgroundFill(Color.rgb(225, 225, 225), null, null)));
        loginButton.layoutXProperty().bind(rootPane.widthProperty().multiply(.5f).subtract(loginButton.widthProperty().divide(2)));
        loginButton.layoutYProperty().bind(rootPane.heightProperty().multiply(.5f).subtract(loginBoundingBox.heightProperty().divide(2).subtract(140)));
        rootPane.getChildren().add(loginButton);

        loginButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                usernameField.setUnFocusColor(Color.RED);
                passwordField.setUnFocusColor(Color.RED);
                loginErrorText.setFill(Color.RED);
            }
        });


        JFXButton newAccountButton = new JFXButton("Create account");
        newAccountButton.layoutXProperty().bind(rootPane.widthProperty().multiply(.5f).subtract(newAccountButton.widthProperty().divide(2)));
        newAccountButton.layoutYProperty().bind(rootPane.heightProperty().multiply(.5f).subtract(loginBoundingBox.heightProperty().divide(2).subtract(240)));
        rootPane.getChildren().add(newAccountButton);



        // ----------- DEFINE/SET STAGE/SCENE -------------
        Scene scene = new Scene(rootPane);
        scene.setFill(Color.GRAY);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
