package server.round;

import server.GameLogic;
import server.player.Player;

public class RoundLogic {
//    private Player currentDealer;
//    private Player smallBlind;
//    private Player bigBlind;

    //IMPORTANT FOR GAMETYPE
    private int currentBettingRoundNumber;
    //IMPORTANT FOR PLAYER ACTIONS
    private SingleBettingRound currentRound;

    private static RoundLogic instance;

    private RoundLogic(){
        resetBettingRoundForNewGame();
    }
    public static RoundLogic getInstance(){
        if(instance == null) instance = new RoundLogic();
        return instance;
    }

    public int getCurrentBettingRoundNumber(){
        return currentBettingRoundNumber;
    }

    public void resetBettingRoundForNewGame(){
        currentBettingRoundNumber = 0;
    }

    public void newRound(){
        currentRound = new SingleBettingRound();
        currentBettingRoundNumber++;
    }

    public void moveToNextRound(){
        if(currentBettingRoundNumber < GameLogic.getInstance().getCurrentGameBeingPlayed().getAmountOfBettingRoundsAllowedPerGame()){
            currentBettingRoundNumber++;
            GameLogic.getInstance().prepareNewRound();
            //GameLogic.getInstance().getCurrentGameBeingPlayed().moveToNextRound();
        }
    }

//    public Player getSmallBlindPlayer(){
//        return smallBlind;
//    }
//    public Player getBigBlindPlayer(){
//        return bigBlind;
//    }
    private SingleBettingRound getCurrentBettingRound(){
        return currentRound;
    }
    public void moveToNextPlayer(){
        currentRound.switchToNextBettingPlayer();
    }

}
