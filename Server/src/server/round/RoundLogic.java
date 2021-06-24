package server.round;

import server.GameLogic;
import server.player.Player;

import java.rmi.RemoteException;

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
        try {
            currentRound = new SingleBettingRound();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        resetBettingRoundForNewRound();
    }
    public static RoundLogic getInstance(){
        if(instance == null) instance = new RoundLogic();
        return instance;
    }

    public int getCurrentBettingRoundNumber(){
        return currentBettingRoundNumber;
    }

    public void resetBettingRoundForNewRound(){
        currentBettingRoundNumber = -1;
    }

    public void newRound() throws RemoteException {
        currentRound = new SingleBettingRound();
        resetBettingRoundForNewRound();
        currentBettingRoundNumber++;
    }

    public void moveToNextRound() throws RemoteException {
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
    public SingleBettingRound getCurrentBettingRound(){
        return currentRound;
    }
    public void moveToNextPlayer() throws RemoteException {
        currentRound.switchToNextBettingPlayer();
    }

}
