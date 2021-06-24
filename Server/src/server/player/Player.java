package server.player;

import javafx.beans.property.SimpleIntegerProperty;
import remoteInterfaces.ICard;
import remoteInterfaces.IPlayer;
import server.GameLogic;
import server.card.Card;
import server.round.RoundLogic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Player extends UnicastRemoteObject implements IPlayer {
    private String name;
    private int funds;
    private ArrayList<Card> pocketCards;
    private boolean folded;
    //TODO: LISTENER ON LASTPLACEDBET THAT CHANGES THE "REACTED THIS ROUND" TO FALSE
    private boolean reactedThisRound;

    public void reactionRequired(){
        if(!folded){
            reactedThisRound = false;
        }
    }

    public boolean isReactionRequired(){
        return !reactedThisRound;
    }

    public Player(String name/*, int initialFunds*/) throws RemoteException {
        super();
        this.name = name;
        //funds = initialFunds;
        folded = false;
        pocketCards = new ArrayList<>();
    }

    @Override
    public ArrayList<ICard> getPocketCards(){
        ArrayList<ICard> generatedPocketCards = new ArrayList<>();
        pocketCards.forEach(card -> generatedPocketCards.add(card));
        return generatedPocketCards;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public void decreaseFundsBy(int amount) {
        bet(amount);
    }

    @Override
    public int getFunds(){
        return funds;
    }

    public void addCardToHand(Card card) throws RemoteException {
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
        RoundLogic.getInstance().getCurrentBettingRound().setBetFromCurrentPlayerBetting(betAmount);
        System.out.println("Remaining Funds:\t"+funds);
    }

    private void throwAwayHand(){
        pocketCards.clear();
    }

//    public ArrayList<Card> getPocketCards(){
//        return pocketCards;
//    }
//    //TODO: DELETE, FOR DEMO-PURPOSES ONLY
////    public int getHandValue(){
////        int pointValue = 0;
////        for(Card card : pocketCards){
////            pointValue += card.getValue();
////        }
//        return pointValue;
//    }

    @Override
    public void fold() throws RemoteException{
        folded = true;
        reactedThisRound = true;
        System.out.println("Folded:\t"+folded);
    }

    public void resetThisPlayerForNewRound(){
        throwAwayHand();
        folded = false;
    }

    @Override
    public boolean hasFolded() throws  RemoteException{
        return folded;
    }
}
