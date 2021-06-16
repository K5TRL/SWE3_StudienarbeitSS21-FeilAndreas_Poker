package remoteInterfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ITestface extends Remote {
    public String sayHello() throws RemoteException;
}
