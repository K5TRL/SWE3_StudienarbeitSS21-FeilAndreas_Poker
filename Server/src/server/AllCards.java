package server;

import server.Card.Card;
import server.Card.CardGenerator;
import server.Card.CardSuit;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class AllCards {
    private static AllCards instance;
    private ArrayList<Card> allCards;

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
        ArrayList<Card> tempArrayList = new ArrayList<>();
        for(int i = 0; i<allCards.size(); i++){
            int position = (int)(Math.random()* allCards.size());
            Card tempCard = allCards.get(position);
            tempArrayList.add(tempCard);
            allCards.remove(tempCard);
        }
        allCards = tempArrayList;
    }

    public Card getRandomCard(){
        return (Card) allCards.get((int)Math.random()*allCards.size());
    }
}
