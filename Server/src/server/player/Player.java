package server.player;

import javafx.beans.property.SimpleIntegerProperty;
import remoteInterfaces.ICard;
import remoteInterfaces.IPlayer;
import server.GameLogic;
import server.card.Card;
import server.round.RoundLogic;
import server.table.PokerTable;
import server.table.Pot;

import java.math.RoundingMode;
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
    public void decreaseFundsBy(int amount) throws RemoteException {
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

    public void bet(int betAmount) throws RemoteException {
        funds -= betAmount;
        RoundLogic.getInstance().getCurrentBettingRound().setBetFromCurrentPlayerBetting(betAmount);
        RoundLogic.getInstance().getCurrentBettingRound().getCurrentPot().increasPotBy(betAmount);
    }

    private void throwAwayHand(){
        pocketCards.clear();
    }

    @Override
    public void fold() throws RemoteException{
        folded = true;
        reactedThisRound = true;
        for (Pot pot : PokerTable.getInstance().getAllPotsInPlay()) {
            pot.removePlayerEligitability(this);
        }
    }

    public void resetBooleanFoldedForNewRound() {
        folded = false;
    }

    public void resetThisPlayerForNewRound(){
        throwAwayHand();
        resetBooleanFoldedForNewRound();
    }

    @Override
    public boolean hasFolded() throws  RemoteException{
        return folded;
    }
}
