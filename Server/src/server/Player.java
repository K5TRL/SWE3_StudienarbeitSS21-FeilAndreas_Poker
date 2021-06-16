package server;

import javafx.collections.ObservableList;

public class Player {
    private String name;
    private int funds;
    private ObservableList cardsOnHand;

    public String getName(){
        return name;
    }
    public int getFunds(){
        return funds;
    }
    public void addCardToHand(Card card){
        if(/*GameTypeValidation? -> how many cards are allowed on hand?*/true){
            cardsOnHand.add(card);
        }
    }
}
