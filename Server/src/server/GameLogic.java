package server;

import server.card.AllCards;
import server.game_type.GameType;
import server.game_type.TexasHoldEm;
import server.player.Player;
import server.player.PlayerManagement;
import server.round.RoundLogic;

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
    }

    public void prepareNewGame(){
        preparePlayersForNewGame();
        RoundLogic.getInstance().resetBettingRoundForNewGame();
    }

    public void prepareNewRound(){
        prepareAllCards();
        resetPlayersforNewRound();
        RoundLogic.getInstance().newRound();
    }

    private void prepareAllCards(){
        AllCards.getInstance().resetFullDeckOfCards();
        AllCards.getInstance().shuffleDeck();
    }

    private void resetPlayersforNewRound(){
        PlayerManagement.getInstance().getAllPlayers().forEach(player -> {
            player.throwAwayHand();
        });
        //Has to be this way, since everyone gets one card at a time!
        for(int i = 0; i<currentGameBeingPlayed.getAmountOfCardsAllowedOnHand(); i++){
            for(Player player : PlayerManagement.getInstance().getAllPlayers()){
                player.addCardToHand(AllCards.getInstance().getRandomCard());
            }
        }
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
