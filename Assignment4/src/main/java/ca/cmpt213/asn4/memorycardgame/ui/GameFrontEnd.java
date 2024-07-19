/**
 * {@code GameFrontEnd} class is responsible for displaying the end pages of the game. It displays the won page and the exit page.
 * It is purely related to the UI of the game.
 *
 * {@code GameFrontEnd} class has the following methods:
 *      ->  {@code revealWonPage(int moves, int time)} displays the won page. It displays the number of moves and the time taken to complete the game.
 *           It also displays the players name. All this is done on a custom background image. This page is shown only when the player has won the game.
 *
 *      ->  {@code revealExitPage(int moves, int time)} displays the exit page. It displays the number of moves and the time taken to complete the game.
 *           It also show the players name. All this is done on a custom background image. This page is shown when the player exits the game.
 */

package ca.cmpt213.asn4.memorycardgame.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
public class GameFrontEnd {

    public void revealWonPage(int moves, int time) {

        String name = StartPage.getPlayerName();
        int minutes = time / 60;
        int seconds = time % 60;
        Stage congratsStage = new Stage();
        congratsStage.setTitle("Congratulations!");
        Label label1 = new Label("Congratulations " + name + "!");
        Label label2 = new Label("You made " + moves + " moves.");
        Label label3 = new Label("You took " + minutes + " minutes and " + seconds + " seconds to complete the game.\n \n \n");
        Font customFont2 = Font.loadFont("file:font/custom2.ttf", 50);
        label1.setFont(customFont2);
        label1.setStyle("-fx-text-fill: #d73e3e;");
        label2.setFont(customFont2);
        label2.setStyle("-fx-text-fill: #d73e3e;");
        label3.setFont(customFont2);
        label3.setStyle("-fx-text-fill: #d73e3e;");

        VBox vBox = new VBox(label1, label2, label3);
        vBox.setAlignment(Pos.BOTTOM_RIGHT);
        Image backgroundImage = new Image("file:img/exit-picture.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(3200 , 1800,  false, false, true, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        vBox.setBackground(new Background(background));

        Scene congratsScene = new Scene(vBox, 1600, 900);
        congratsScene.setFill(Color.BLACK);
        congratsStage.setScene(congratsScene);
        congratsStage.setResizable(false);
        congratsStage.show();
    }

    public void revealExitPage(int moves, int time){
        String name = StartPage.getPlayerName();

        int minutes = time / 60;
        int seconds = time % 60;

        Stage exitStage = new Stage();
        Label label1 = new Label("Thank you for playing the game " + name + "!    ");
        Label label2 = new Label("You made " + moves + " moves.    ");
        Label label3 = new Label("You played for " + minutes + " minutes and " + seconds + " seconds.    \n \n \n");
        Font customFont2 = Font.loadFont("file:font/custom2.ttf", 50);
        label1.setFont(customFont2);
        label1.setStyle("-fx-text-fill: #d73e3e;");
        label2.setFont(customFont2);
        label2.setStyle("-fx-text-fill: #d73e3e;");
        label3.setFont(customFont2);
        label3.setStyle("-fx-text-fill: #d73e3e;");


        VBox vBox2 = new VBox(label1, label2, label3);
        vBox2.setAlignment(Pos.BOTTOM_RIGHT);
        vBox2.setSpacing(20);

        Image backgroundImage = new Image("file:img/exit-picture.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(backgroundImage.getHeight() , backgroundImage.getWidth(),  false, false, true, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background rulesBackground = new Background(background);

        vBox2.setBackground(rulesBackground);

        Scene exitScene = new Scene(vBox2, 1600 , 900);
        exitScene.setFill(Color.BLACK);
        exitStage.setScene(exitScene);
        exitStage.setResizable(false);
        exitStage.show();
    }
}
