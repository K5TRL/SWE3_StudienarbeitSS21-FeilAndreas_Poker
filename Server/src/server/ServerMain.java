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
        //ServerSkeleton skeleton = new ServerSkeleton();
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind(IServerSkeleton.class.getName(), new ServerSkeleton());
        //registry.rebind(ITestface.class.getName(), UnicastRemoteObject.exportObject(new Testface(),8088));
        //registry.rebind(IGameActions.class.getName(), UnicastRemoteObject.exportObject(new GameLogic.getInstance(),8088));
//        registry = LocateRegistry.createRegistry(8089);
//        registry.rebind(IPlayerActions.class.getName(), UnicastRemoteObject.exportObject(new PlayerActions(), 8089));
//        registry = LocateRegistry.createRegistry(8090);
//        registry.rebind(IPlayer.class.getName(), UnicastRemoteObject.exportObject(new Player("Andreas",GameLogic.getBuyIn()), 8090));
    }
    public static void main(String[] args) throws InterruptedException, RemoteException {
        System.out.println("Server boot successful.");
        startServer();
    }
}
