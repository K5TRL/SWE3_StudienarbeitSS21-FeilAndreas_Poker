package server;

import javafx.application.Platform;
import remoteInterfaces.IGameLogic;
import remoteInterfaces.IPlayer;
import remoteInterfaces.IPlayerManagement;
import remoteInterfaces.IServerSkeleton;
import server.player.Player;
import server.player.PlayerManagement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
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

    //TODO:REMOVE
    @Override
    public void exitApp() throws RemoteException {
        System.exit(0);
    }
}
