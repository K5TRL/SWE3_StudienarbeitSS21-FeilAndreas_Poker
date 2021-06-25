package remoteInterfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICard extends Remote {
    int getValue() throws RemoteException;
    String getSuit() throws RemoteException;
}
