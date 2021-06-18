package server;

import remoteInterfaces.IPlayerActions;
import remoteInterfaces.ITestface;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerMain {
    public static void startServer() throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(8088);
        registry.rebind(ITestface.class.getName(), UnicastRemoteObject.exportObject(new Testface(),8088));
        registry = LocateRegistry.createRegistry(8089);
        registry.rebind(IPlayerActions.class.getName(), UnicastRemoteObject.exportObject(new PlayerActions(), 8089));
    }
    public static void main(String[] args) throws InterruptedException, RemoteException {
        System.out.println("Server boot successful.");
        startServer();
    }
}
