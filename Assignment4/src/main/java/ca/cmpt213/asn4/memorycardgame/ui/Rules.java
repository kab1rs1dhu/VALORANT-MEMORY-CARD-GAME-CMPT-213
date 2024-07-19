/**
 * {@code Rules} class is used to display the rules of the game to the user. Its responsibility is to show the user the rules if the user wants to view them.
 * It also shows the user all the characters of the game for their reference.
 *
 * {@code Rules }class has the following methods:
 *
 *     ->  {@code showRules()} - shows the rules of the game to the user in a new window. The rules window contains rules in text.
 *          It also has a close window button, if the user clicks on that button then the rules window closes.
 *          It also opens a background window that shows all the characters of the game. The background window has a play button that starts the game.
 *
 *     ->  {@code addAnimationToButton(Button button)} - adds animation to the button when the mouse enters, exits, pressed and released.
 *
 *     {@code Rules} is only used to shw the rules in a new window. It does not interact with the game logic.
 */


package ca.cmpt213.asn4.memorycardgame.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Rules {

    public void showRules() {
        Stage rulesStage = new Stage();
        Stage backgroundStage = new Stage();
        rulesStage.setTitle("Rules of the Game");

        Image backgroundImage = new Image("file:img/all-characters-image.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background rulesBackground = new Background(background);

        Font customFont = Font.loadFont("file:font/custom2.ttf", 20);
        Font headingFont = Font.loadFont("file:font/custom2.ttf", 40);
        Font subHeadingFont = Font.loadFont("file:font/custom2.ttf", 30);

        Label infoLabel = new Label("You are playing a Memory card game based upon the popular game 'VALORANT'.\n You need to find all the matching pairs of the 8 characters of the game. \n" +
                "When you find all the cards, you win the game. \n" +
                "Press 'Play Game' to start the game. \n" +
                "Press 'RESET GAME' to start a new game. \n" +
                "Press 'EXIT GAME' to exit the game. \n" +
                "Press 'I' to view the instructions again. \n" +
                "All the characters are shown to you for your own reference \n" +
                "Best of luck!");
        infoLabel.setFont(customFont);
        infoLabel.setStyle("-fx-text-fill: #d73e3e;");
        infoLabel.setPadding(new Insets(10));

        Label rulesHeadingLabel = new Label("Game Rules");
        rulesHeadingLabel.setFont(headingFont);
        rulesHeadingLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");
        rulesHeadingLabel.setPadding(new Insets(10, 0, 10, 0));
        rulesHeadingLabel.setAlignment(Pos.CENTER);

        Label rulesLabel = new Label("1. Click on a card to reveal it.\n2. Find the matching card in the game board.\n3. Match all pairs to win.\n4. Press 'I' to view the instructions again. \n5. Press 'RESET GAME' to start a new game.");
        rulesLabel.setFont(customFont);
        rulesLabel.setStyle("-fx-text-fill: #d73e3e;");
        rulesLabel.setPadding(new Insets(10));

        Button playButton = new Button("Play Game");
        playButton.setFont(subHeadingFont);
        playButton.setStyle("-fx-background-color: #d73e3e; -fx-text-fill: white;");
        playButton.setOnAction(event -> {
            rulesStage.close();
            backgroundStage.close();
        });

        addAnimationToButton(playButton);

        Button closeWindow = new Button("Close Window");
        closeWindow.setFont(subHeadingFont);
        closeWindow.setStyle("-fx-background-color: #d73e3e; -fx-text-fill: white;");
        closeWindow.setOnAction(event -> {
            rulesStage.close();
        });

        addAnimationToButton(closeWindow);

        VBox rulesBox = new VBox(rulesHeadingLabel, infoLabel, rulesLabel, closeWindow, playButton);
        rulesBox.setSpacing(20);
        rulesBox.setStyle("-fx-background-color: black;");
        rulesBox.setPadding(new Insets(20));
        rulesBox.setAlignment(Pos.CENTER);

        VBox backgroundBox = new VBox();
        backgroundBox.setBackground(rulesBackground);
        backgroundBox.setPadding(new Insets(15, 15, 15, 15));
        backgroundBox.setAlignment(Pos.BOTTOM_CENTER);
        backgroundBox.getChildren().add(playButton);

        Scene rulesScene = new Scene(rulesBox, 800, 800);
        Scene backgroundScene = new Scene(backgroundBox, 1600, 900);

        rulesStage.setScene(rulesScene);
        backgroundStage.setScene(backgroundScene);
        backgroundStage.setResizable(false);
        rulesStage.setResizable(false);
        backgroundStage.show();
        rulesStage.show();
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

}

