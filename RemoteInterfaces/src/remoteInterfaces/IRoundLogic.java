package remoteInterfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRoundLogic extends Remote {
    int getCurrentBettingRoundNumber() throws RemoteException;
}
