/**
 * {@code StartPage} class is the first page of the game where the player enters their name to start the game.
 *  It asks for the name using a TextFiled, if the name is empty then it asks the player to enter the name again.
 *
 * {@code StartPage} class has the following fields:
 *    -> {@code playerName} - a string representing the name of the player.
 *
 *  {@code StartPage} class has the following methods:
 *     -> {@code start(Stage primaryStage)} - starts the game by asking the player to enter their name. It has a textFiled where the player can enter their name.
 *         If the player does not enter the name then it asks the player to enter the name again. Or if the name is empty, it asks for it again.
 *         It also has two buttons, one to start the game and the other to show the rules of the game. if the user enters 'ENTER', even then the game is started.
 *         If the user enters 'ESC', then the game is closed.
 *
 *      -> {@code addAnimationToButton(Button button)} - adds animation to the button when the mouse enters, exits, pressed and released.
 *
 *      -> {@code transitionToGame(Stage primaryStage)} - transitions to the game page when the player enters their name.
 *
 *      -> {@code getPlayerName()} - returns the name of the player.
 */


package ca.cmpt213.asn4.memorycardgame.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class StartPage {

    private static String playerName;

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Memory Card Game - Start Page");

        Font customFont = Font.loadFont("file:font/custom2.ttf", 70);
        Font customFont2 = Font.loadFont("file:font/custom2.ttf", 30);
        Font buttonFont = Font.loadFont("file:font/custom2.ttf", 30);

        Label nameLabel = new Label("Hello Gamer! Please enter your name");
        nameLabel.setFont(customFont);
        nameLabel.setStyle("-fx-text-fill: #d73e3e;");
        nameLabel.setAlignment(Pos.BOTTOM_CENTER);

        TextField nameField = new TextField();
        nameField.setFont(customFont);
        nameField.setStyle("-fx-text-fill: #d73e3e;");
        nameField.setBackground(new Background(new BackgroundFill(new Color(1, 1, 1, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        nameField.setMaxWidth(300);

        Label instructions = new Label("Press 'ENTER' to start the game or press 'ESC' to exit the game");
        instructions.setFont(customFont2);
        instructions.setStyle("-fx-text-fill: #d73e3e;");
        instructions.setAlignment(Pos.BOTTOM_CENTER);

        Button startButton = new Button("Start Game");
        startButton.setFont(buttonFont);
        startButton.setStyle("-fx-background-color: #d73e3e; -fx-text-fill: white;");
        addAnimationToButton(startButton);

        startButton.setOnAction(event -> {
            String playerName = nameField.getText().trim();
            if (!playerName.trim().isEmpty()) {
                this.playerName = nameField.getText();
                transitionToGame(primaryStage);
            } else {
                nameLabel.setText("Name cannot be empty. Please enter your name:");
            }
        });

        Button rulesButton = new Button("Rules");
        rulesButton.setFont(buttonFont);
        rulesButton.setStyle("-fx-background-color: #d73e3e; -fx-text-fill: white;");
        addAnimationToButton(rulesButton);

        rulesButton.setOnAction(event -> {
            Rules rules = new Rules();
            rules.showRules();
        });

        HBox buttonBox = new HBox(50, startButton, rulesButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(150, nameLabel, nameField, buttonBox, instructions);
        vBox.setAlignment(Pos.CENTER);

        Image backgroundImage = new Image("file:img/start-page-image.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(backgroundImage.getHeight(), backgroundImage.getWidth(), false, false, true, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        vBox.setBackground(new Background(background));

        Scene scene = new Scene(vBox, 1600, 900);
        scene.setFill(Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER:
                    String playerName = nameField.getText().trim();
                    if (!playerName.trim().isEmpty()) {
                        this.playerName = nameField.getText();
                        transitionToGame(primaryStage);
                    } else {
                        nameLabel.setText("Name cannot be empty. Please enter your name:");
                    }
                    break;
                case ESCAPE:
                    primaryStage.close();
                    break;
            }
        });
    }

    private void addAnimationToButton(Button button) {
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.DARKRED);
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            button.setEffect(shadow);
            button.setScaleX(1.1);
            button.setScaleY(1.1);
        });

        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            button.setEffect(null);
            button.setScaleX(1.0);
            button.setScaleY(1.0);
        });

        button.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            button.setScaleX(1.2);
            button.setScaleY(1.2);
        });

        button.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
            button.setScaleX(1.1);
            button.setScaleY(1.1);
        });
    }

    private void transitionToGame(Stage primaryStage) {
        GameUI gameUI = new GameUI();
        try {
            gameUI.start(primaryStage);
            primaryStage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getPlayerName() {
        return playerName;
    }
}

