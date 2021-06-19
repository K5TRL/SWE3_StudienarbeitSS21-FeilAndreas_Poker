package server;

import server.Card.Card;
import server.Card.CardGenerator;
import server.Card.CardSuit;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Stack;

public class AllCards {
    private static AllCards instance;
    //TODO: CHANGE TO STACK
    private Stack<Card> allCards;

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
        int amountOfCardsLeftToIterateOver = allCards.size();
        for(int i = 0; i<allCards.size(); i++){
            //int position = (int)(Math.random()* allCards.size());
            Card tempCard = allCards.get((int)(Math.random()* amountOfCardsLeftToIterateOver));
            amountOfCardsLeftToIterateOver--;
            tempArrayList.add(tempCard);
        }
        allCards.clear();
        for(Card card : tempArrayList){
            allCards.push(card);
        }
    }

    public Card getRandomCard(){
        return allCards.pop();
    }

    //IT IS TRADITION TO BURN A CARD BEFORE PLAYING THE FLOP, TURN AND RIVER
    public void burnCard(){
        allCards.pop();
    }
}
