package server;

import remoteInterfaces.IPlayerActions;

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
        System.out.println("Call.");
    }

    /*
    * TODO: Every action needs to trigger switchToNextBettingPlayer() in SingleBettingRound
    *
    *
    */
}
