package server.round;

import server.player.Player;
import server.player.PlayerManagement;
import server.GameLogic;
import server.settings.Blinds;
import server.table.PokerTable;
import server.table.Pot;

import java.util.ArrayList;

//ONE SINGLE BETTING ROUND
public class SingleBettingRound {
    private Player currentPlayerBetting;
    private Player playerWhoSetMostRecentBet;
    private int latestBetPlaced;
    private ArrayList<Pot> potsCreatedInThisRound;
    private Pot currentPot;
    private ArrayList<Player> allPlayers;

    public SingleBettingRound(){
        potsCreatedInThisRound.add(new Pot(PlayerManagement.getInstance().getAllPlayers()));
        currentPot = potsCreatedInThisRound.get(0);
        ArrayList<Player> allPlayers = PlayerManagement.getInstance().getAllPlayers();
        currentPlayerBetting = PlayerManagement.getInstance().getSmallBlindPlayer();
        latestBetPlaced = 0;
    }

    //TODO: create side-pot if one player doesn't have the funds to bet along but still went all in.

    public void addPotsToTable(){
        for(Pot pot : potsCreatedInThisRound){
            PokerTable.getInstance().addPotToTable(pot);
        }
    }

    private boolean roundIsCompleted(){
//        ArrayList<Player> allPlayers = PlayerManagement.getInstance().getAllPlayers();
        int index = allPlayers.indexOf(currentPlayerBetting);
        Player nextPlayer = allPlayers.get(index+1);
        if(nextPlayer.equals(playerWhoSetMostRecentBet)){
            return true;
        }
        return false;
    }

    public void switchToNextBettingPlayer(){
        if(roundIsCompleted()){
            addPotsToTable();
            GameLogic.getInstance().prepareNewRound();
        }
        else{
            int index = allPlayers.indexOf(currentPlayerBetting);
            currentPlayerBetting = allPlayers.get((index+1)%allPlayers.size());
        }
    }
    public void setBetFromCurrentPlayerBetting(int amount){
        currentPlayerBetting.bet(amount);
        playerWhoSetMostRecentBet = currentPlayerBetting;
        latestBetPlaced = amount;
    }
    public Player getCurrentPlayerBetting(){return currentPlayerBetting;}

    public void addNewPotToCurrentRound(Pot pot){
        potsCreatedInThisRound.add(pot);
    }

    public Player getPlayerWhoSetMostRecentBet(){return playerWhoSetMostRecentBet;}

    public int getLatestBetPlaced(){
        if(latestBetPlaced>100){
            return latestBetPlaced;
        }
        else return Blinds.getInstance().getSmallBlindAmount();
    }
}
