package server;

import java.util.ArrayList;

//ONE SINGLE BETTING ROUND
public class SingleBettingRound {
    private Player currentPlayerBetting;
    private Player playerWhoSetMostRecentBet;
    private ArrayList<Pot> potsCreatedInThisRound;
    private Pot currentPot;

    public SingleBettingRound(){
        potsCreatedInThisRound.add(new Pot(PlayerManagement.getInstance().getAllPlayers()));
        currentPot = potsCreatedInThisRound.get(0);
    }

    //TODO: create side-pot if one player doesn't have the funds to bet along but still went all in.

    public void addPotsToTable(){
        for(Pot pot : potsCreatedInThisRound){
            PokerTable.getInstance().addPotToTable(pot);
        }
    }
}
