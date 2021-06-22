package server.player;

import remoteInterfaces.IPlayer;
import server.GameLogic;
import server.card.Card;

import java.util.ArrayList;

public class Player implements IPlayer {
    private String name;
    private int funds;
    private ArrayList<Card> pocketCards;
    private boolean folded;

    public Player(String name, int initialFunds){
        this.name = name;
        funds = initialFunds;
        folded = false;
    }

    @Override
    public String getName(){
        return name;
    }
    @Override
    public int getFunds(){
        return funds;
    }

    public void addCardToHand(Card card){
        if(pocketCards.size()< GameLogic.getInstance().getCurrentGameBeingPlayed().getAmountOfCardsAllowedOnHand()){
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

    private void throwAwayHand(){
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

    public void fold(){
        folded = true;
    }

    public void resetThisPlayerForNewRound(){
        throwAwayHand();
        folded = false;
    }

    public boolean hasFolded(){
        return folded;
    }
}
