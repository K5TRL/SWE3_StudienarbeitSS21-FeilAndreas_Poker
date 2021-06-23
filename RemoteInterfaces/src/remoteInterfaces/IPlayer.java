package remoteInterfaces;

import javafx.beans.property.SimpleIntegerProperty;
import server.player.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPlayer extends Remote {
    SimpleIntegerProperty getFunds();
    String getName();
    void decreaseFundsBy(int amount);
}
