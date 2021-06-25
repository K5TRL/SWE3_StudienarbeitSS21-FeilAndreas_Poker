package server;

import javafx.application.Platform;
import remoteInterfaces.*;
import server.player.Player;
import server.player.PlayerManagement;
import server.round.RoundLogic;
import server.table.PokerTable;

import java.math.RoundingMode;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServerSkeleton extends UnicastRemoteObject implements IServerSkeleton {
    //private final int port;

    public ServerSkeleton() throws RemoteException {
        super();
        //this.port = port;
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
        //RoundLogic.getInstance().moveToNextRound();
    }

    @Override
    public ArrayList<ICard> getCardsOnTable() throws RemoteException {
        return (ArrayList<ICard>) PokerTable.getInstance().getAllCardsOnTable();
    }

}
