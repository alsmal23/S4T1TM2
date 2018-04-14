package edu.csus.yaam.client.demo;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.beans.binding.DoubleBinding;


/**
 * A demo, and hopefully mostly reusable, example of what creating a new account could look like
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
        int elementLeading = 20;

        double paddingPercent = 0.1;
        DoubleBinding contentWidth = rootPane.widthProperty().multiply(.4f);
        DoubleBinding contentHeight = rootPane.heightProperty().multiply(.8f);

        Pane contentPane = new Pane();
        contentPane.prefWidthProperty().bind(contentWidth); //want this to be flexible based on window size
        contentPane.prefHeightProperty().bind(contentHeight);
        contentPane.minWidth(300);
        contentPane.layoutXProperty().bind(rootPane.widthProperty().multiply(.5f).subtract(contentPane.widthProperty().divide(2)));
        contentPane.layoutYProperty().bind(rootPane.heightProperty().multiply(.5f).subtract(contentPane.heightProperty().divide(2)));
        contentPane.setStyle("-fx-background-color: SNOW;");
        contentPane.setStyle("-fx-border-color: GRAY;");
        rootPane.getChildren().add(contentPane);

        StackPane newUserNameTitle = new StackPane(new Text("Your new username:"));
        newUserNameTitle.setAlignment(Pos.CENTER_LEFT);
        newUserNameTitle.layoutXProperty().bind(contentPane.widthProperty().multiply(paddingPercent));
        newUserNameTitle.layoutYProperty().bind(contentPane.heightProperty().multiply(paddingPercent));
        newUserNameTitle.prefWidthProperty().bind(contentWidth.subtract(contentWidth.multiply(paddingPercent*2)));
        contentPane.getChildren().add(newUserNameTitle);

        JFXTextField newUserNameField = new JFXTextField();
        newUserNameField.setPromptText("You will login with this name");
        newUserNameField.setFocusColor(Color.GREEN);
        newUserNameField.layoutXProperty().bind(contentPane.widthProperty().multiply(paddingPercent));
        newUserNameField.layoutYProperty().bind(newUserNameTitle.layoutYProperty().add(newUserNameTitle.heightProperty()).add(elementLeading/3));
        newUserNameField.prefWidthProperty().bind(contentWidth.subtract(contentWidth.multiply(paddingPercent*2)));
        contentPane.getChildren().add(newUserNameField);

        StackPane newDisplayNameTitle = new StackPane(new Text("Your new display name:"));
        newDisplayNameTitle.setAlignment(Pos.CENTER_LEFT);
        newDisplayNameTitle.layoutXProperty().bind(contentPane.widthProperty().multiply(paddingPercent));
        newDisplayNameTitle.layoutYProperty().bind(newUserNameField.layoutYProperty().add(newUserNameField.heightProperty()).add(elementLeading));
        newDisplayNameTitle.prefWidthProperty().bind(contentWidth.subtract(contentWidth.multiply(paddingPercent*2)));
        contentPane.getChildren().add(newDisplayNameTitle);

        JFXTextField newDisplayNameField = new JFXTextField();
        newDisplayNameField.setPromptText("Other users will see this name");
        newDisplayNameField.setFocusColor(Color.GREEN);
        newDisplayNameField.layoutXProperty().bind(contentPane.widthProperty().multiply(paddingPercent));
        newDisplayNameField.layoutYProperty().bind(newDisplayNameTitle.layoutYProperty().add(newDisplayNameTitle.heightProperty()).add(elementLeading/3));
        newDisplayNameField.prefWidthProperty().bind(contentWidth.subtract(contentWidth.multiply(paddingPercent*2)));
        contentPane.getChildren().add(newDisplayNameField);

        StackPane newPasswordTitle = new StackPane(new Text("Your new password:"));
        newPasswordTitle.setAlignment(Pos.CENTER_LEFT);
        newPasswordTitle.layoutXProperty().bind(contentPane.widthProperty().multiply(paddingPercent));
        newPasswordTitle.layoutYProperty().bind(newDisplayNameField.layoutYProperty().add(newDisplayNameField.heightProperty().add(elementLeading)));
        newPasswordTitle.prefWidthProperty().bind(contentWidth.subtract(contentWidth.multiply(paddingPercent*2)));
        contentPane.getChildren().add(newPasswordTitle);

        JFXPasswordField passwordFieldNew = new JFXPasswordField();
        passwordFieldNew.setPromptText("New password");
        passwordFieldNew.setFocusColor(Color.GREEN);
        passwordFieldNew.setStyle("-fx-text-box-border: Snow;");
        passwordFieldNew.layoutXProperty().bind(contentPane.widthProperty().multiply(paddingPercent));
        passwordFieldNew.layoutYProperty().bind(newPasswordTitle.layoutYProperty().add(newPasswordTitle.heightProperty()).add(elementLeading/3));
        passwordFieldNew.prefWidthProperty().bind(contentWidth.subtract(contentWidth.multiply(paddingPercent*2)));
        contentPane.getChildren().add(passwordFieldNew);

        JFXPasswordField passwordFieldConfirm = new JFXPasswordField();
        passwordFieldConfirm.setPromptText("Confirm new password");
        passwordFieldConfirm.setFocusColor(Color.GREEN);
        passwordFieldConfirm.setStyle("-fx-text-box-border: Snow;");
        passwordFieldConfirm.layoutXProperty().bind(contentPane.widthProperty().multiply(paddingPercent));
        passwordFieldConfirm.layoutYProperty().bind(passwordFieldNew.layoutYProperty().add(passwordFieldNew.heightProperty().add(elementLeading/3)));
        passwordFieldConfirm.prefWidthProperty().bind(contentWidth.subtract(contentWidth.multiply(paddingPercent*2)));
        contentPane.getChildren().add(passwordFieldConfirm);

        JFXButton createAccountButton = new JFXButton("Create Account"); //Can we make this bold?
        createAccountButton.setBackground(new Background(new BackgroundFill(Color.rgb(225, 225, 225), null, null)));
        createAccountButton.layoutXProperty().bind(contentPane.widthProperty().multiply(.5f).subtract(createAccountButton.widthProperty().divide(2)));
        createAccountButton.layoutYProperty().bind(passwordFieldConfirm.layoutYProperty().add(passwordFieldConfirm.heightProperty().add(elementLeading*3)));
        contentPane.getChildren().add(createAccountButton);

        JFXButton cancelButton = new JFXButton("Cancel"); //Can we make this bold?
        //cancelButton.setBackground(new Background(new BackgroundFill(Color.rgb(225, 225, 225), null, null)));
        cancelButton.layoutXProperty().bind(contentPane.widthProperty().multiply(.5f).subtract(cancelButton.widthProperty().divide(2)));
        cancelButton.layoutYProperty().bind(createAccountButton.layoutYProperty().add(createAccountButton.heightProperty().add(elementLeading/3)));
        contentPane.getChildren().add(cancelButton);


        // ----------- DEFINE/SET STAGE/SCENE -------------
        Scene scene = new Scene(rootPane);
        scene.setFill(Color.LIGHTGRAY);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
