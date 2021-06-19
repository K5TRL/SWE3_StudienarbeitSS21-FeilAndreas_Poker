package server;

import server.Card.Card;

import java.util.ArrayList;

public class Player {
    private String name;
    private int funds;
    private ArrayList<Card> pocketCards;

    public Player(String name, int initialFunds){
        this.name = name;
        funds = initialFunds;
    }

    public String getName(){
        return name;
    }
    public int getFunds(){
        return funds;
    }

    public void addCardToHand(Card card){
        if(pocketCards.size()<GameLogic.getInstance().getCurrentGameBeingPlayed().getAmountOfCardsAllowedOnHand()){
            pocketCards.add(card);
        }
    }
    public void addFunds(int funds){
        this.funds+=funds;
    }

    public void setFunds(int funds){
        this.funds = funds;
    }

    public void bet(int betAmount){
        funds -= betAmount;
    }

    public void throwAwayHand(){
        pocketCards.clear();
    }

    public ArrayList<Card> getPocketCards(){
        return pocketCards;
    }
    //TODO: DELETE, FOR DEMO-PURPOSES ONLY
    public int getHandValue(){
        int pointValue = 0;
        for(Card card : pocketCards){
            pointValue += card.getValue();
        }
        return pointValue;
    }
}
