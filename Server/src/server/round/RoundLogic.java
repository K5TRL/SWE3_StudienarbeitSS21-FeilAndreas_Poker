package server.round;

import remoteInterfaces.IRoundLogic;
import server.GameLogic;
import server.player.Player;
import server.table.PokerTable;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RoundLogic extends UnicastRemoteObject implements IRoundLogic {

    //IMPORTANT FOR GAMETYPE
    private int currentBettingRoundNumber;
    //IMPORTANT FOR PLAYER ACTIONS
    private SingleBettingRound currentRound;

    private static RoundLogic instance;

    private RoundLogic() throws RemoteException {
        super();
        try {
            currentRound = new SingleBettingRound();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        resetBettingRoundForNewRound();
    }
    public static RoundLogic getInstance() throws RemoteException {
        if(instance == null) instance = new RoundLogic();
        return instance;
    }

    @Override
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

    public void newSingleBettingRound() throws RemoteException{
        PokerTable.getInstance().addPotToTable(RoundLogic.getInstance().getCurrentBettingRound().getCurrentPot());
        currentRound = new SingleBettingRound();
        currentBettingRoundNumber++;
    }

    public void moveToNextRound() throws RemoteException {
        if(currentBettingRoundNumber < GameLogic.getInstance().getCurrentGameBeingPlayed().getAmountOfBettingRoundsAllowedPerGame()){
            currentBettingRoundNumber++;
            GameLogic.getInstance().prepareNewRound();
        }
    }

    public SingleBettingRound getCurrentBettingRound(){
        return currentRound;
    }
    public void moveToNextPlayer() throws RemoteException {
        currentRound.switchToNextBettingPlayer();
    }

}
