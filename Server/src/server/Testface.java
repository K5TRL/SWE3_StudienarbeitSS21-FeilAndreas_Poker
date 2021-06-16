package server;

import remoteInterfaces.ITestface;

import java.rmi.RemoteException;

public class Testface implements ITestface {
    @Override
    public String sayHello() throws RemoteException {
        return "Server sais hello";
    }
}
