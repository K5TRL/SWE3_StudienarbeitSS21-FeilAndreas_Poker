package remoteInterfaces;

import server.Card;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICard extends Remote {
    public Card getCard() throws RemoteException;
}
