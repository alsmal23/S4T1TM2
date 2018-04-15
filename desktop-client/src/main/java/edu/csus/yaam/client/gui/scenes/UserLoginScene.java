package edu.csus.yaam.client.gui.scenes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import edu.csus.yaam.client.gui.YaamStage;
import edu.csus.yaam.client.gui.javafx.PathView.Path;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.SneakyThrows;

/**
 * @author Ryan R
 * @date 4/12/2018
 */
public class UserLoginScene implements YaamScene {
    private final YaamStage stage;
    @Getter
    private final Pane scene;

    @SneakyThrows
    public UserLoginScene(YaamStage stage) {
        this.stage = stage;

        // ----------- PANE -------------
        scene = new Pane();


        //------------ BOUNDING BOX ------------ Focuses the user onto important elements of the page
        Rectangle loginBoundingBox = new Rectangle();
        loginBoundingBox.setWidth(250);
        loginBoundingBox.setHeight(300);
        loginBoundingBox.layoutXProperty().bind(scene.widthProperty().multiply(.5f).subtract(loginBoundingBox.widthProperty().divide(2)));
        loginBoundingBox.layoutYProperty().bind(scene.heightProperty().multiply(.5f).subtract(loginBoundingBox.heightProperty().divide(2)));
        loginBoundingBox.setFill(Color.SNOW);
        loginBoundingBox.setStroke(Color.GRAY);
        scene.getChildren().add(loginBoundingBox);


        // ----------- INPUTS AND BUTTONS -------------
        JFXTextField usernameField = new JFXTextField();
        usernameField.setPromptText("Username");
        usernameField.layoutXProperty().bind(scene.widthProperty().multiply(.5f).subtract(usernameField.widthProperty().divide(2)));
        usernameField.layoutYProperty().bind(scene.heightProperty().multiply(.5f).subtract(loginBoundingBox.heightProperty().divide(2).subtract(30)));
        usernameField.setFocusColor(Color.GREEN);
        scene.getChildren().add(usernameField);


        JFXPasswordField passwordField = new JFXPasswordField();
        passwordField.setPromptText("Password");
        passwordField.setFocusColor(Color.GREEN);
        passwordField.setStyle("-fx-text-box-border: Snow;");
        passwordField.layoutXProperty().bind(scene.widthProperty().multiply(.5f).subtract(passwordField.widthProperty().divide(2)));
        passwordField.layoutYProperty().bind(scene.heightProperty().multiply(.5f).subtract(loginBoundingBox.heightProperty().divide(2).subtract(70)));
        scene.getChildren().add(passwordField);


        Text loginErrorText = new Text("Account not found");
        loginErrorText.setFill(Color.SNOW);
        loginErrorText.layoutXProperty().bind(scene.widthProperty().multiply(.5f).subtract(passwordField.widthProperty().divide(2)));
        loginErrorText.layoutYProperty().bind(scene.heightProperty().multiply(.5f).subtract(loginBoundingBox.heightProperty().divide(2).subtract(120)));
        scene.getChildren().add(loginErrorText);


        JFXButton loginButton = new JFXButton("LOGIN"); //Can we make this bold?
        loginButton.setBackground(new Background(new BackgroundFill(Color.rgb(225, 225, 225), null, null)));
        loginButton.layoutXProperty().bind(scene.widthProperty().multiply(.5f).subtract(loginButton.widthProperty().divide(2)));
        loginButton.layoutYProperty().bind(scene.heightProperty().multiply(.5f).subtract(loginBoundingBox.heightProperty().divide(2).subtract(140)));
        scene.getChildren().add(loginButton);

        loginButton.setOnMouseClicked(event -> {
            usernameField.setUnFocusColor(Color.RED);
            passwordField.setUnFocusColor(Color.RED);
            loginErrorText.setFill(Color.RED);
        });

        JFXButton newAccountButton = new JFXButton("Create account");
        newAccountButton.layoutXProperty().bind(scene.widthProperty().multiply(.5f).subtract(newAccountButton.widthProperty().divide(2)));
        newAccountButton.layoutYProperty().bind(scene.heightProperty().multiply(.5f).subtract(loginBoundingBox.heightProperty().divide(2).subtract(240)));
        newAccountButton.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> stage.navigate(CreateAccountScene.class));
        scene.getChildren().add(newAccountButton);
    }

    @Override
    public void show() {
        stage.setPath(Path.of("Sign In", FontAwesomeIcon.SIGN_IN));
    }
}