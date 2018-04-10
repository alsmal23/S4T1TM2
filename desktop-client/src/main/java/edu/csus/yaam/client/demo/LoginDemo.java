package edu.csus.yaam.client.demo;


import com.jfoenix.controls.JFXPasswordField;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
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
        Rectangle r = new Rectangle();
        r.setX(280);
        r.setY(120);
        r.setWidth(250);
        r.setHeight(300);
        r.setFill(Color.SNOW);
        r.setStroke(Color.GRAY);
        rootPane.getChildren().add(r);


        // ----------- INPUTS AND BUTTONS -------------
        JFXTextField usernameField = new JFXTextField();
        usernameField.setPromptText("Username");
        usernameField.layoutXProperty().bind(rootPane.widthProperty().multiply(.5f).subtract(usernameField.widthProperty().divide(2)));
        usernameField.layoutYProperty().bind(rootPane.heightProperty().multiply(.3).subtract(usernameField.heightProperty().divide(2)));
        usernameField.setFocusColor(Color.GREEN);
        //username.setUnFocusColor(Color.RED);  // To display red if the username or password is incorrect
        rootPane.getChildren().add(usernameField);


        JFXPasswordField passwordField = new JFXPasswordField();
        passwordField.setPromptText("Password");
        passwordField.setFocusColor(Color.GREEN);
        //password.setUnFocusColor(Color.RED); // To display red if the username or password is incorrect
        passwordField.setStyle("-fx-text-box-border: Snow;");
        passwordField.layoutXProperty().bind(rootPane.widthProperty().multiply(.5f).subtract(passwordField.widthProperty().divide(2)));
        passwordField.layoutYProperty().bind(rootPane.heightProperty().multiply(.4).subtract(passwordField.heightProperty().divide(2)));
        rootPane.getChildren().add(passwordField);


        JFXButton loginButton = new JFXButton("LOGIN");
        loginButton.setStyle("-fx-font-family: 'Montserrat SemiBold'");
        loginButton.setBackground(new Background(new BackgroundFill(Color.rgb(225, 225, 225), null, null)));
        loginButton.layoutXProperty().bind(rootPane.widthProperty().multiply(.5f).subtract(loginButton.widthProperty().divide(2)));
        loginButton.layoutYProperty().bind(rootPane.heightProperty().multiply(.5).subtract(loginButton.heightProperty().divide(2)));
        rootPane.getChildren().add(loginButton);

        loginButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Clicked! Is shift down: " + event.isShiftDown());
                usernameField.setUnFocusColor(Color.RED);
                passwordField.setUnFocusColor(Color.RED);
            }
        });


        JFXButton newAccountButton = new JFXButton("Create account");
        newAccountButton.layoutXProperty().bind(rootPane.widthProperty().multiply(.5f).subtract(newAccountButton.widthProperty().divide(2)));
        newAccountButton.layoutYProperty().bind(rootPane.heightProperty().multiply(.65).subtract(newAccountButton.heightProperty().divide(2)));
        rootPane.getChildren().add(newAccountButton);



        // ----------- DEFINE/SET STAGE/SCENE -------------
        Scene scene = new Scene(rootPane);
        scene.setFill(Color.GRAY);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
