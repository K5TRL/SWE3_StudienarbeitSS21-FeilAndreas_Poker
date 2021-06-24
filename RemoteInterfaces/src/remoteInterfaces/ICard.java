package remoteInterfaces;

import server.card.Card;
import server.card.CardSuit;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICard extends Remote {
    int getValue() throws RemoteException;
    String getSuit() throws RemoteException;
}
