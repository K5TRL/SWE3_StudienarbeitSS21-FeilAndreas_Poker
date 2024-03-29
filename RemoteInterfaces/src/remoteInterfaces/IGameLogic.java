package remoteInterfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGameLogic extends Remote {
    void startNewGame() throws RemoteException;
    void startNewRound() throws RemoteException;
    void moveToNextBettingRound() throws RemoteException;
    int getBuyIn() throws RemoteException;
    void changeBuyIn(int amount) throws RemoteException;
    int getMinimalBetAllowed() throws RemoteException;
}
