package server.card;

import java.util.*;

public class AllCards {
    private static AllCards instance;
    //TODO: CHANGE TO STACK
    private List<Card> allCards;

    private AllCards(){

    }

    public static AllCards getInstance(){
        if(instance==null){
            instance = new AllCards();
        }
        return instance;
    }

    public void resetFullDeckOfCards(){
        allCards = CardGenerator.getInstance().createFullDeck();
//        EnumSet.allOf(CardSuit.class).forEach(cardSuit -> {
//            for(int i = 0; i<13; i++){
//                allCards.add(new Card(i, cardSuit));
//            }
//        });
    }

    public void shuffleDeck(){
        Collections.shuffle(allCards);
    }

    public Card getRandomCard(){
        return allCards.remove(0);
    }

    //IT IS TRADITION TO BURN A CARD BEFORE PLAYING THE FLOP, TURN AND RIVER
    public void burnCard(){
        allCards.remove(0);
    }
}
