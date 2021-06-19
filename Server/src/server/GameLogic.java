package server;

import javafx.scene.control.Alert;
import server.GameType.GameType;
import server.GameType.TexasHoldEm;

import java.math.RoundingMode;

public class GameLogic {

    private static final int BUY_IN = 1000;
    private static GameLogic instance;
    private GameType currentGameBeingPlayed;

    private GameLogic(){
        setCurrentGameBeingPlayedToTexasHoldEm();
    }
    public static GameLogic getInstance(){
        if(instance==null){
            instance = new GameLogic();
        }
        return instance;
    }

    public void setCurrentGameBeingPlayedToTexasHoldEm(){
        currentGameBeingPlayed = new TexasHoldEm();
    }

    public GameType getCurrentGameBeingPlayed(){
        return currentGameBeingPlayed;
        //return currentGameBeingPlayed;
    }

    public void prepareNewGame(){
        preparePlayersForNewGame();
        RoundLogic.getInstance().resetBettingRoundForNewGame();
    }

    public void prepareNewRound(){
        prepareAllCards();
        resetPlayersforNewRound();
    }

    private void prepareAllCards(){
        AllCards.getInstance().resetFullDeckOfCards();
        AllCards.getInstance().shuffleDeck();
    }

    private void resetPlayersforNewRound(){
        PlayerManagement.getInstance().getAllPlayers().forEach(player -> {
            player.throwAwayHand();
        });
    }

    private void preparePlayersForNewGame(){
        PlayerManagement.getInstance().getAllPlayers().forEach(player -> {
            player.setFunds(BUY_IN);
        });
    }

    public void executeCurrentRoundRules(){
        currentGameBeingPlayed.executeCurrentRoundRules();
    }





}
