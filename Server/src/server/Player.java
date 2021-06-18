package server;

import javafx.collections.ObservableList;
import server.Card.Card;

import java.util.ArrayList;

public class Player {
    private String name;
    private int funds;
    private ArrayList<Card> cardsOnHand;

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
        if(cardsOnHand.size()<GameLogic.getInstance().getCurrentGameBeingPlayed().getAmountOfCardsAllowedOnHand()){
            cardsOnHand.add(card);
        }
    }
    public void addFunds(int funds){
        this.funds+=funds;
    }

    public void setFunds(int funds){
        this.funds = funds;
    }

    public void throwAwayHand(){
        cardsOnHand.clear();
    }
}
