package server.card;

import java.util.EnumSet;
import java.util.Stack;

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
    public Stack<Card> createFullDeck(){
        Stack<Card> allCards = new Stack<>();
        EnumSet.allOf(CardSuit.class).forEach(cardSuit -> {
            for(int i = 1; i<=13; i++){
                try {
                    Card card = new Card(i, cardSuit);
                    allCards.push(card);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        return allCards;
    }
}
