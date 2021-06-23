package remoteInterfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface IPlayerManagement extends Remote {
    //IPlayerManagement getPlayerManagement() throws RemoteException;
    IPlayer getPlayer(String name) throws RemoteException;
}
