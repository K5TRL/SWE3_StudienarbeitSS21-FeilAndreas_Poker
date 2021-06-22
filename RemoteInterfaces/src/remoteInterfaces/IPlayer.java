package remoteInterfaces;

import server.player.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPlayer extends Remote {
    int getFunds();
    String getName();
}
