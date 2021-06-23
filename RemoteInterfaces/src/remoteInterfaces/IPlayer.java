package remoteInterfaces;

import javafx.beans.property.SimpleIntegerProperty;
import server.player.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPlayer extends Remote {
    int getFunds() throws RemoteException;
    String getName() throws RemoteException;
    void decreaseFundsBy(int amount) throws RemoteException;
}
