package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientLauncher {
    public static void main(String[] args) throws NotBoundException, RemoteException, InterruptedException {
        ClientMain.main(args);
    }
}
