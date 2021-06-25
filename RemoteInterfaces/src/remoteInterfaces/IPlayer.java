package remoteInterfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IPlayer extends Remote {
    int getFunds() throws RemoteException;
    String getName() throws RemoteException;
    void decreaseFundsBy(int amount) throws RemoteException;
    ArrayList<ICard> getPocketCards() throws RemoteException;
    void fold() throws RemoteException;
    boolean hasFolded() throws RemoteException;
    void resetBooleanFoldedForNewRound() throws RemoteException;
}
