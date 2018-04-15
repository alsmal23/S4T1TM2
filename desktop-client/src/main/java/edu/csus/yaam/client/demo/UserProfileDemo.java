package edu.csus.yaam.client.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


/**
 * A demo, and hopefully mostly reusable, example of what updating an account could look like
 *
 * @author Lindsay Haven
 * @date 04/14/2018
 */


public class UserProfileDemo extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // ----------- STAGE -------------
        primaryStage.setTitle("Login");
        primaryStage.setHeight(600);
        primaryStage.setWidth(800);


        // ----------- PANE -------------
        Pane rootPane = new Pane();


        //------------ BOUNDING BOX ------------ Focuses the user onto important elements of the page
        Rectangle profileBoundingBox = new Rectangle();
        profileBoundingBox.setWidth(250);
        profileBoundingBox.setHeight(300);
        profileBoundingBox.layoutXProperty().bind(rootPane.widthProperty().multiply(.5f).subtract(profileBoundingBox.widthProperty().divide(2)));
        profileBoundingBox.layoutYProperty().bind(rootPane.heightProperty().multiply(.5f).subtract(profileBoundingBox.heightProperty().divide(2)));
        profileBoundingBox.setFill(Color.SNOW);
        profileBoundingBox.setStroke(Color.GRAY);
        rootPane.getChildren().add(profileBoundingBox);


        // ----------- DEFINE/SET STAGE/SCENE -------------
        Scene scene = new Scene(rootPane);
        scene.setFill(Color.LIGHTGRAY);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
