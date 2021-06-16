package client;

import remoteInterfaces.ITestface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientMain {
    public static void startClient() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(8088);
        ITestface testface = (ITestface) registry.lookup(ITestface.class.getName());
        System.out.println(testface.sayHello());
    }
    public static void main(String[] args) throws InterruptedException, NotBoundException, RemoteException {
        System.out.println("Client boot successful.");
        startClient();
    }
}
