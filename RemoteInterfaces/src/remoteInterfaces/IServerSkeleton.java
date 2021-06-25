package remoteInterfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IServerSkeleton extends Remote {
    IGameLogic getGameLogic() throws RemoteException;
    IPlayerManagement getPlayerManagement() throws RemoteException;
    IRoundLogic getRoundLogic() throws RemoteException;
    int getLatestPlacedBid() throws RemoteException;
    //TODO:REMOVE
    void exitApp() throws RemoteException;
    void continueToNextPlayer() throws RemoteException;
    ArrayList<ICard> getCardsOnTable() throws RemoteException;
}
