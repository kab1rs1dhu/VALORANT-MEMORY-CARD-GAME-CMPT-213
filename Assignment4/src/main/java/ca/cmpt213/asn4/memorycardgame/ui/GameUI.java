/**
 * {@code GameUI} class is responsible for the game play of the Memory Card Game. It belongs to the UI package as it is related to the user interface of the game
 * It has a ImageView [] [] array that stores 16 IMAGE_VIEWS. It uses the GameLogic go implement all the logics of the game.
 *
 * {@code GameUI} class has the following fields:
 *     -> {@code IMAGE_VIEWS} is a 2D array that stores the IMAGE_VIEWS of the game. This is the fundamental part of the game as it stores the images of the cards.
 *        When the user clicks on one of the IMAGE_VIEWS in the array, the index is sent to gameLogic and on the corresponding int [][] in {@code GameLogic},
 *        it is checked which image is present at that index. The image is then displayed on the screen using the backend to provide us the image url.
 *
 *      -> {@code game} is an object of {@code GameLogic} class. It is used to implement all the logics of the game.
 *
 *      -> {@code backImage} is an object of {@code Image} class. It is used to store the image of the card that is hidden, this can be because the card is never revealed
 *         or because the card is not matched.
 *
 *      -> {@code frontEnd} is an object of {@code GameFrontEnd} class. It is used to display the different windows of the game.
 *
 *      -> {@code rulesUI} is an object of {@code Rules} class. It is used to display the rules of the game.
 *
 *      -> {@code moves} is an integer that stores the number of moves made by the player.
 *
 *      -> {@code timeline} is an object of {@code Timeline} class. It is used to keep track of the time elapsed in the game.
 *
 *      -> {@code secondsElapsed} is an integer that stores the number of seconds elapsed in the game.
 *
 *      -> {@code timerLabel} is an object of {@code Label} class. It is used to display the time elapsed in the game.
 *
 *      -> {@code movesLabel} is an object of {@code Label} class. It is used to display the number of moves made by the player.
 *
 * {@code GameUI} class has the following methods:
 *
 *     -> {@code start(Stage stage)} is the main method of the class. It is used to start the game. The user is able to interact with this method to play the game.
 *         It handles all the clicks made by the user and displays the images of the cards on the screen. It also displays the time elapsed and the number of moves made by the player.
 *         It also has the buttons and the labels in the game. It has a reset button which resets the game. By resetting the game, the timer and the moves is set to 0
 *         and the game board is shuffled again. There is also an exit button. if the user clicks on that button, the game is closed and a thank-you window is displayed.
 *         This thank-you window also shows the players name, number of moves and the time taken to complete the game.
 *         If the user clicks on the 'I' key, the rules of the game are displayed.
 *
 *      -> {@code resetGame()} is a method that resets the game. It is used to reset the game. It stops the timer, sets the seconds elapsed and the moves to 0.
 *
 *      -> {@code revealWithAnimation(int i , int j)} is a method that reveals the card with the given index. It reveals the card using a fade-in and fade-out animation.
 *
 *      -> {@code hideCards()} is a method that hides the cards. It is used to hide the cards that are not matched.
 *
 *      -> {@code updateTimerLabel(Label timerLabel)} is a method that updates the timer label. It updates the timer by calculating the minutes and seconds elapsed using seconds elapsed.
 *
 *      -> {@code updateMoveLabel(Label movesLabel)} is a method that updates the move label. When the player selects two cards, the move label is incremented by 1.
 *
 *      -> {@code won()} is a method that checks if the player has won the game. It checks if all the cards are matched and the checked cards size is 16.
 *          If the player has won the game, a congratulations window is displayed showing the players name, the number of moves and the time taken.
 *
 *      -> {@code checkIfAllMatched()} is a method that checks if all the cards are matched. It checks if all the cards are matched by checking if the checked cards list contains all the cards.
 *
 *      -> {@code resetAllCards()} is a method that resets all the cards. It is used to reset all the cards in the game.
 *
 * {@code GameUI} is basically the main game page of the whole application. it is where the user can interact with the game.
 */

package ca.cmpt213.asn4.memorycardgame.ui;

import ca.cmpt213.asn4.memorycardgame.game.GameLogic;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;

public class GameUI{

    private final ImageView[] [] IMAGE_VIEWS = new ImageView[4][4];
    private final GameLogic GAME = new GameLogic();
    private final Image backImage = new Image("file:img/back-image1.png");
    private final GameFrontEnd frontEnd = new GameFrontEnd();
    private final Rules rulesUI = new Rules();
    private int moves = 0;
    private Timeline timeline;
    private int secondsElapsed = 0;
    private Label timerLabel;
    private Label movesLabel;

    public void start(Stage stage) {

        Font customFont = Font.loadFont("file:font/custom2.ttf", 30);

        this.timerLabel = new Label("          Time: 00:00");
        timerLabel.setFont(customFont);
        timerLabel.setStyle("-fx-text-fill: #d73e3e;");
        VBox timerVbox = new VBox(timerLabel);
        timerVbox.setAlignment(Pos.TOP_LEFT);
        timerVbox.setPadding(new Insets(0, 0, 0, 70));

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            secondsElapsed++;
            updateTimerLabel(timerLabel);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


        Label instructionLabel = new Label("        Press 'I' for Instructions");
        instructionLabel.setFont(customFont);
        instructionLabel.setStyle("-fx-text-fill: #d73e3e;");
        VBox instructionVbox = new VBox(instructionLabel);
        instructionVbox.setAlignment(Pos.BOTTOM_LEFT);


        Label gameLabel = new Label("Memory Card Game");
        Font customFont1 = Font.loadFont("file:font/custom2.ttf", 80);
        gameLabel.setFont(customFont1);
        gameLabel.setStyle("-fx-text-fill: #d73e3e;");
        gameLabel.setAlignment(Pos.CENTER_RIGHT);
        VBox gameVbox = new VBox(gameLabel);
        gameVbox.setAlignment(Pos.TOP_CENTER);

        stage.setTitle("Memory Card Game");

        this.movesLabel = new Label("Moves: " + moves);
        movesLabel.setFont(customFont);
        movesLabel.setStyle("-fx-text-fill: #d73e3e;");
        VBox movesVbox = new VBox(movesLabel);
        movesVbox.setAlignment(Pos.BOTTOM_RIGHT);

        GridPane gridPane = new GridPane();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                IMAGE_VIEWS[i][j] = new ImageView(backImage);
                IMAGE_VIEWS[i][j].setFitHeight(280);
                IMAGE_VIEWS[i][j].setFitWidth(280);
                IMAGE_VIEWS[i][j].setPreserveRatio(true);
                final int finalI = i;
                final int finalJ = j;
                IMAGE_VIEWS[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if(GAME.checkedCardsContains(GAME.getID(finalI, finalJ))){
                            return;
                        }
                        if (GAME.selectedCardsContains(GAME.getID(finalI, finalJ))) {
                            return;
                        } else {
                            GAME.addToSelectedCards(GAME.getID(finalI, finalJ));
                            revealWithAnimation(finalI, finalJ);
                            if(GAME.getSelectedCardsSize() == 2) {
                                moves++;
                                updateMoveLabel(movesLabel);
                                if (!(GAME.checkCards(finalI, finalJ))) {
                                    hideCards();
                                }
                                PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                                pause.setOnFinished(event1 -> {
                                });
                            }
                            if (won()) {
                                timeline.stop();
                                PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                                pause.setOnFinished(event1 -> {
                                    stage.close();
                                    frontEnd.revealWonPage(moves, secondsElapsed);
                                });
                                pause.play();
                            }
                        }
                    }
                });
                gridPane.add(IMAGE_VIEWS[i][j], i, j);
            }
        }

        gridPane.setPadding(new Insets(5, 5, 5, 5));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(javafx.geometry.Pos.CENTER);

        Button resetButton = new Button("RESET GAME");
        addAnimationToButton(resetButton);
        resetButton.setOnAction(event -> {
            resetGame();
            GAME.clearCheckedCards();
            GAME.clearSelectedCards();
            PauseTransition pause = new PauseTransition(Duration.seconds(500));
            pause.play();
        });

        Button exitButton = new Button("EXIT GAME");
        addAnimationToButton(exitButton);
        exitButton.setOnAction(event -> {
            frontEnd.revealExitPage(moves, secondsElapsed);
            stage.close();

        });
        Font buttonFont = Font.loadFont("file:font/custom2.ttf", 20);
        exitButton.setStyle("-fx-background-color: #d73e3e;");
        exitButton.setFont(buttonFont);

        resetButton.setStyle("-fx-background-color: #d73e3e;");
        resetButton.setFont(buttonFont);

        HBox buttonHbox = new HBox(resetButton, exitButton , movesVbox);
        buttonHbox.setAlignment(javafx.geometry.Pos.CENTER);
        buttonHbox.setSpacing(20);

        VBox vBox = new VBox( timerVbox, gridPane , instructionVbox, buttonHbox);

        vBox.setAlignment(javafx.geometry.Pos.CENTER);
        Image backgroundImage = new Image("file:img/main-page-image2.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(backgroundImage.getHeight() , backgroundImage.getWidth(), false, false, true, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        vBox.setBackground(new Background(background));;
        Scene scene = new Scene(vBox, 1600 , 900);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()){
                case ESCAPE:
                    stage.close();
                    break;
                case I:
                    rulesUI.showRules();
                    break;
            }
        });
    }
    private void resetGame() {
        timeline.stop();
        secondsElapsed = 0;
        moves = 0;
        updateTimerLabel(timerLabel);
        updateMoveLabel(movesLabel);
        resetAllCards();
        GAME.reset();
        timeline.play();
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

    private void revealWithAnimation(int i , int j){
        FadeTransition fadeOut = new FadeTransition(Duration.millis(50), IMAGE_VIEWS[i][j]);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(event -> {
            Image tempImage = new Image(GAME.getImgUrl(GAME.getID(i, j)));
            IMAGE_VIEWS[i][j].setImage(tempImage);
            FadeTransition fadeIn = new FadeTransition(Duration.millis(40), IMAGE_VIEWS[i][j]);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        });
        fadeOut.play();
    }

    private void hideCards() {
        PauseTransition pause = new PauseTransition(Duration.seconds(0.50));
        pause.setOnFinished(event -> {
            for (int k = 0; k < 4; k++) {
                for (int l = 0; l < 4; l++) {
                    if (!GAME.checkedCardsContains(GAME.getID(k, l))) {
                        IMAGE_VIEWS[k][l].setImage(backImage);
                    }
                }
            }
        });
        pause.play();
    }
    private void updateTimerLabel(Label timerLabel) {
        int minutes = secondsElapsed / 60;
        int seconds = secondsElapsed % 60;
        timerLabel.setText(String.format("Time: %02d:%02d", minutes, seconds));
    }


    private void updateMoveLabel(Label movesLabel) {
        movesLabel.setText("Moves: " + moves);
    }


    private boolean won() {
        return (checkIfAllMatched() && GAME.getCheckedCardsSize() == 16);
    }

    private boolean checkIfAllMatched(){
        for(int i=0; i<16; i++){
            if(!GAME.checkedCardsContains(i)){
                return false;
            }
        }
        return true;
    }

    private void resetAllCards(){
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                IMAGE_VIEWS[i][j].setImage(backImage);
            }
        }
    }
}

