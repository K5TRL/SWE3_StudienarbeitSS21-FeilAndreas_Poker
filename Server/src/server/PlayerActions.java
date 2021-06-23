package server;

import remoteInterfaces.IPlayerActions;
import server.player.PlayerManagement;
import server.round.RoundLogic;

import java.awt.*;
import java.math.RoundingMode;
import java.rmi.RemoteException;

public class PlayerActions implements IPlayerActions {

    private static PlayerActions instance;

    public PlayerActions(){

    }

//    public static PlayerActions getInstance() {
//        if(instance==null){
//            instance = new PlayerActions();
//        }
//        return instance;
//    }


    @Override
    public PlayerActions getPlayerActions() throws RemoteException {
        return this;
    }

    @Override
    public void call() throws RemoteException {
        RoundLogic.getInstance().getCurrentBettingRound().getCurrentPlayerBetting().bet(RoundLogic.getInstance().getCurrentBettingRound().getLatestBetPlaced());
        continueToNextPlayer();
    }

    @Override
    public void fold() throws RemoteException {
        RoundLogic.getInstance().getCurrentBettingRound().getCurrentPlayerBetting().fold();
        continueToNextPlayer();
    }

    @Override
    public void raise(int amount) throws RemoteException {
        if( /*maybe validate over at client*/RoundLogic.getInstance().getCurrentBettingRound().getCurrentPlayerBetting().getFunds().getValue()>amount){
            RoundLogic.getInstance().getCurrentBettingRound().setBetFromCurrentPlayerBetting(amount);
        }
        continueToNextPlayer();
    }

    /*
    * TODO: Every action needs to trigger switchToNextBettingPlayer() in SingleBettingRound
    *
    *
    */
    private void continueToNextPlayer(){
        RoundLogic.getInstance().getCurrentBettingRound().switchToNextBettingPlayer();
    }
}
