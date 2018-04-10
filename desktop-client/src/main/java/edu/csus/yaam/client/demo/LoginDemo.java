package edu.csus.yaam.client.demo;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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


        // ----------- USER NAME TEXT BOX -------------
        JFXTextField field = new JFXTextField();
        field.setPromptText("Placeholder value...");
        primaryStage.titleProperty().bind(field.textProperty());
        field.layoutXProperty().bind(rootPane.widthProperty().multiply(.5f).subtract(field.widthProperty().divide(2)));
        field.layoutYProperty().bind(rootPane.heightProperty().multiply(.5).subtract(field.heightProperty().divide(2)));
        field.setFocusColor(Color.GREEN);
        field.setUnFocusColor(Color.RED);
        field.setStyle("-fx-background-color: light-gray;");
        rootPane.getChildren().add(field);


        // ----------- DEFINE/SET STAGE/SCENE -------------
        Scene scene = new Scene(rootPane);
        scene.setFill(Color.GRAY);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
