package remoteInterfaces;

import server.PlayerActions;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPlayerActions extends Remote {

    public PlayerActions getPlayerActions() throws RemoteException;
    public void call() throws RemoteException;

}
