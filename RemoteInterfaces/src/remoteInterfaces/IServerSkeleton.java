package remoteInterfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServerSkeleton extends Remote {
    IGameLogic getGameLogic() throws RemoteException;
    IPlayerManagement getPlayerManagement() throws RemoteException;
}
