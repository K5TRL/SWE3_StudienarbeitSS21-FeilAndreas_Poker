package server;

import remoteInterfaces.*;
import server.player.Player;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerMain {
    public static void startServer() throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind(IServerSkeleton.class.getName(), new ServerSkeleton());
    }
    public static void main(String[] args) throws InterruptedException, RemoteException {
        System.out.println("Server boot successful.");
        startServer();
    }
}
