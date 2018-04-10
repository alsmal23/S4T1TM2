package edu.csus.yaam.client.demo;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.geometry.BoundingBox;
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
        primaryStage.setTitle("Default Title");
        primaryStage.setHeight(600);
        primaryStage.setWidth(800);


        // ----------- PANE -------------
        Pane rootPane = new Pane();


        //------------ BOUNDING BOX ------------ Focuses the user onto important elements of the page
        //BoundingBox loginBox = new BoundingBox(50, 50, 100, 100); //double minX, double minY, double width, double height
        //rootPane.getChildren().add(loginBox);

        // ----------- INPUTS AND BUTTONS -------------
        JFXTextField username = new JFXTextField();
        username.setPromptText("Placeholder value...");
        username.layoutXProperty().bind(rootPane.widthProperty().multiply(.5f).subtract(username.widthProperty().divide(2)));
        username.layoutYProperty().bind(rootPane.heightProperty().multiply(.3).subtract(username.heightProperty().divide(2)));
        username.setFocusColor(Color.GREEN);
        username.setUnFocusColor(Color.RED);
        rootPane.getChildren().add(username);

        JFXTextField password = new JFXTextField();
        password.setPromptText("Placeholder value...");
        password.layoutXProperty().bind(rootPane.widthProperty().multiply(.5f).subtract(password.widthProperty().divide(2)));
        password.layoutYProperty().bind(rootPane.heightProperty().multiply(.4).subtract(password.heightProperty().divide(2)));
        password.setFocusColor(Color.GREEN);
        password.setUnFocusColor(Color.RED);
        password.setStyle("-fx-text-box-border: LightGray;");
        rootPane.getChildren().add(password);

        JFXButton loginButton = new JFXButton("Login");
        loginButton.layoutXProperty().bind(rootPane.widthProperty().multiply(.5f).subtract(password.widthProperty().divide(2)));
        loginButton.layoutYProperty().bind(rootPane.heightProperty().multiply(.5).subtract(password.heightProperty().divide(2)));
        rootPane.getChildren().add(loginButton);

        JFXButton newAccountButton = new JFXButton("Create a New Account");
        newAccountButton.layoutXProperty().bind(rootPane.widthProperty().multiply(.5f).subtract(password.widthProperty().divide(2)));
        newAccountButton.layoutYProperty().bind(rootPane.heightProperty().multiply(.6).subtract(password.heightProperty().divide(2)));
        rootPane.getChildren().add(newAccountButton);



        // ----------- DEFINE/SET STAGE/SCENE -------------
        Scene scene = new Scene(rootPane);
        scene.setFill(Color.GRAY);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
