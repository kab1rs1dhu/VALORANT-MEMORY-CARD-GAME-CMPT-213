/**
 * MemoryGame class is the main class that starts the game by creating a StartPage object and calling its start method.
 */

package ca.cmpt213.asn4.memorycardgame;

import ca.cmpt213.asn4.memorycardgame.ui.StartPage;
import javafx.application.Application;
import javafx.stage.Stage;

public class MemoryGame extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        StartPage startPage = new StartPage();
        startPage.start(stage);
    }
}
