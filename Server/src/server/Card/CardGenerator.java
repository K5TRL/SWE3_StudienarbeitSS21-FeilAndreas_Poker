package server.Card;

import java.util.ArrayList;
import java.util.EnumSet;

public class CardGenerator {

    private static CardGenerator instance;
    private CardGenerator(){

    }
    public static CardGenerator getInstance(){
        if(instance == null){
            instance = new CardGenerator();
        }
        return instance;
    }
    public ArrayList<Card> createFullDeck(){
        ArrayList<Card> allCards = new ArrayList<>();
        EnumSet.allOf(CardSuit.class).forEach(cardSuit -> {
            for(int i = 0; i<13; i++){
                allCards.add(new Card(i, cardSuit));
            }
        });
        return allCards;
    }
}
