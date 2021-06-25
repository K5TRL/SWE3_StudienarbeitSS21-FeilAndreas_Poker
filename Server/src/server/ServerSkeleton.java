package server;

import remoteInterfaces.*;
import server.player.PlayerManagement;
import server.round.RoundLogic;
import server.table.PokerTable;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ServerSkeleton extends UnicastRemoteObject implements IServerSkeleton {

    public ServerSkeleton() throws RemoteException {
        super();
        System.getProperty("java.security.policy","server.policy");
        System.out.println("ServerSkeleton successfully started!");
    }

    @Override
    public IGameLogic getGameLogic() throws RemoteException {
        return GameLogic.getInstance();
    }

    @Override
    public IPlayerManagement getPlayerManagement() throws RemoteException {
        return PlayerManagement.getInstance();
    }

    @Override
    public IRoundLogic getRoundLogic() throws RemoteException {
        return RoundLogic.getInstance();
    }

    @Override
    public int getLatestPlacedBid() throws RemoteException {
        return RoundLogic.getInstance().getCurrentBettingRound().getLatestPlacedBid();
    }

    //TODO:REMOVE
    @Override
    public void exitApp() throws RemoteException {
        System.exit(0);
    }

    @Override
    public void continueToNextPlayer() throws RemoteException {
        GameLogic.getInstance().prepareNewSingleBettingRound();
    }

    @Override
    public ArrayList<ICard> getCardsOnTable() throws RemoteException {
        return (ArrayList<ICard>) PokerTable.getInstance().getAllCardsOnTable();
    }
}
