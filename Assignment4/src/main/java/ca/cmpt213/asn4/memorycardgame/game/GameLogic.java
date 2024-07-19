/**
 * {@Code GameLogic} class is responsible for the logic of the game. It is responsible for all logic of the game.
 * It belongs to the game logic as it is related to the fundamental logic of the game.
 * It only handles the logic of the game and does not interact with the user interface.
 *
 * {@code GameLogic} class has the following fields:
 *      -> {@code gameBoard} is a 2D array that stores the game board
 *      -> {@code SIZE} is a constant that stores the size of the game board
 *      -> {@code gameBackEnd} is an object of {@code GameBackEnd} class
 *      -> {@code selectedCards} is a list that stores the selected cards
 *      -> {@code checkedCards} is a list that stores the checked cards
 *
 * {@code GameLogic} class has the following methods:
 *
 *      -> {@code initializeGameBoard()} initializes the game board and also shuffles the game board so that all the cards are in random order.
 *          while initializing the game board, it assigns the id to each card
 *
 *      -> {@code getID(int i , int j)} returns the id of the card at the given position
 *
 *      -> {@code shuffleBoard(int [][] gameBoard)} shuffles the game board so that all the cards are in random order.
 *
 *      -> {@code matched(List <Integer> selectedCards)} checks if the selected cards are matched or not
 *
 *      -> {@code reset()} resets the game board
 *
 *      -> {@code getImgUrl(int id)} returns the image url of the card with the given id. This function uses the backend to return the image.
 *          the backend is responsible for storing the image urls of the cards. All the image urls are stored in the backend and are mapped to a number which is the id.
 *
 *      -> {@code addToSelectedCards(int id)} adds the card with the given id to the selected cards list
 *
 *      -> {@code selectedCardsContains(int id)} checks if the selected cards list contains the card with the given id
 *
 *      -> {@code addToCheckedCards(int id)} adds the card with the given id to the checked cards list
 *
 *      -> {@code checkedCardsContains(int id)} checks if the checked cards list contains the card with the given id
 *
 *      -> {@code clearSelectedCards()} clears the selected cards list
 *
 *      -> {@code clearCheckedCards()} clears the checked cards list
 *
 *      -> {@code getSelectedCardsSize()} returns the size of the selected cards list
 *
 *      -> {@code getCheckedCardsSize()} returns the size of the checked cards list
 *
 *      -> {@code checkCards(int i , int j)} checks if the selected cards are matched or not. It checks whether they are the same by calling the backend.
 *          The cards are same if they have the same image url.
 */

package ca.cmpt213.asn4.memorycardgame.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameLogic{

    private int [][] gameBoard;
    private final int SIZE = 16;
    private GameBackEnd gameBackEnd = new GameBackEnd();
    private List<Integer> selectedCards = new ArrayList<>();
    private List<Integer> checkedCards = new ArrayList<>();

    public GameLogic(){
        gameBoard = new int[SIZE/4][SIZE/4];
        initializeGameBoard();
    }

    public void initializeGameBoard(){
        int counter = 0;

        for(int i=0; i<SIZE/4; i++){
            for(int j=0; j<SIZE/4; j++){
                gameBoard[i][j] = counter;
                counter++;
            }
        }
        shuffleBoard(gameBoard);
    }

    public int getID(int i , int j){
        return gameBoard[i][j];
    }

    private void shuffleBoard(int [][] gameBoard){
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<SIZE/4; i++){
            for(int j=0; j<SIZE/4; j++){
                list.add(gameBoard[i][j]);
            }
        }
        Collections.shuffle(list);
        int counter = 0;
        for(int i=0; i<SIZE/4; i++){
            for(int j=0; j<SIZE/4; j++){
                gameBoard[i][j] = list.get(counter);
                counter++;
            }
        }
    }

    public boolean matched(List <Integer> selectedCards){
        return gameBackEnd.checkIfMatched(selectedCards.get(0) , selectedCards.get(1));
    }

    public void reset(){
        initializeGameBoard();
    }

    public String getImgUrl(int id){
        return gameBackEnd.getImgUrl(id);
    }

    public void addToSelectedCards(int id){
        selectedCards.add(id);
    }

    public boolean selectedCardsContains(int id){
        return selectedCards.contains(id);
    }

    public void addToCheckedCards(int id){
        checkedCards.add(id);
    }

    public boolean checkedCardsContains(int id){
        return checkedCards.contains(id);
    }

    public void clearSelectedCards(){
        selectedCards.clear();
    }

    public void clearCheckedCards(){
        checkedCards.clear();
    }

    public int getSelectedCardsSize(){
        return selectedCards.size();
    }

    public int getCheckedCardsSize(){
        return checkedCards.size();
    }


    public boolean checkCards(int i , int j) {
        if (selectedCards.size() == 2) {
            if (matched(selectedCards)) {
                checkedCards.add(selectedCards.get(0));
                checkedCards.add(selectedCards.get(1));
                selectedCards.clear();
                return true;
            } else {
                selectedCards.clear();
            }
        }
        return false;
    }
}
