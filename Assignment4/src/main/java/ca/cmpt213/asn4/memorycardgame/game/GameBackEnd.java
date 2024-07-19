/**
 * {@code GameBackEnd} class is responsible for storing the image urls and checking if the two cards are matched.
 * The class has a hashmap that stores the image urls of the cards. The image urls are mapped to a number which is the id.
 *
 *
 * {@code GameBackEnd} has the following attributes:
 *    -> {@code imageMap} is a hashmap that stores the image urls of the cards. The image urls are mapped to a number which is the id.
 *
 * {@code GameBackEnd} class has the following methods:
 *
 *   -> {@code GameBackEnd()} is the constructor of the class. It initializes the hashmap with the image urls of the cards.
 *
 *    -> {@code checkIfMatched(int id1 , int id2)} checks if the two cards are matched or not. It does that by comparing the image url of the two cards.
 *
 *    -> {@code getImgUrl(int id)} returns the image url of the card with the given id.
 */

package ca.cmpt213.asn4.memorycardgame.game;

import javafx.scene.image.Image;
import java.util.HashMap;

public class GameBackEnd {
    private HashMap<Integer , Image> imageMap = new HashMap<>();

    public GameBackEnd(){
        imageMap.put(0 , new Image("file:img/jett-image.jpg"));
        imageMap.put(1 , new Image("file:img/omen-image.jpg"));
        imageMap.put(2 , new Image("file:img/phoneix-image.jpg"));
        imageMap.put(3 , new Image("file:img/raze-image.jpg"));
        imageMap.put(4 , new Image("file:img/reyna-image.jpg"));
        imageMap.put(5 , new Image("file:img/sage-image.jpg"));
        imageMap.put(6 , new Image("file:img/skye-image.jpg"));
        imageMap.put(7 , new Image("file:img/sova-image.jpg"));

        imageMap.put(8 , new Image("file:img/jett-image.jpg"));
        imageMap.put(9 , new Image("file:img/omen-image.jpg"));
        imageMap.put(10 , new Image("file:img/phoneix-image.jpg"));
        imageMap.put(11 , new Image("file:img/raze-image.jpg"));
        imageMap.put(12 , new Image("file:img/reyna-image.jpg"));
        imageMap.put(13 , new Image("file:img/sage-image.jpg"));
        imageMap.put(14 , new Image("file:img/skye-image.jpg"));
        imageMap.put(15 , new Image("file:img/sova-image.jpg"));
    }

    public boolean checkIfMatched(int id1 , int id2){
        return getImgUrl(id1).equals(getImgUrl(id2));
    }

    public String getImgUrl(int id) {
        return imageMap.get(id).getUrl();
    }
}
