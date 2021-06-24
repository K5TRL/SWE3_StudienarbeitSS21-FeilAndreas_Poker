package client;

import remoteInterfaces.IPlayer;
import remoteInterfaces.IPlayerManagement;
import remoteInterfaces.IServerSkeleton;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientStub {
    //here, all the things the client needs from the server can be gotten

    private Registry registry;

    private IPlayerManagement playerManagement;
    private IServerSkeleton skeleton;
    private IPlayer thePlayer;
    private int minimalBetAllowed;

    private static ClientStub instance;
    private ClientStub(){
        System.getProperty("java.security.policy","client.policy");
        setRegistry();
        setSkeleton();
    }

    private void setSkeleton() {
        try{
            this.skeleton = (IServerSkeleton) registry.lookup(IServerSkeleton.class.getName());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void setRegistry() {
        try{
            registry = LocateRegistry.getRegistry(Registry.REGISTRY_PORT);
        }
        catch (RemoteException e){
            System.err.println("Registry not found. Check if port set correctly.");
            e.printStackTrace();
        }
    }

    public static ClientStub getInstance(){
        if(instance==null){
            instance = new ClientStub();
        }
        return instance;
    }

    public void startNewTexasHoldEmGame(){
        try{
            if(registry == null){
                setRegistry();
            }
//            IGameActions gameActions = (IGameActions) registry.lookup(IGameActions.class.getName());
            if(skeleton == null){
                setSkeleton();
            }
            minimalBetAllowed = skeleton.getGameLogic().getMinimalBetAllowed();
            skeleton.getGameLogic().startNewGame();
//            gameActions.startNewGame();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void moveToNextBettingRound(){
        try {
            skeleton.getGameLogic().moveToNextBettingRound();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void startNewRound(){
        try {
            skeleton.getGameLogic().startNewRound();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setBuyIn(int amount) throws RemoteException {
        skeleton.getGameLogic().changeBuyIn(amount);
    }

    public int getBuyIn() throws RemoteException{
        return skeleton.getGameLogic().getBuyIn();
    }

    private void setPlayerManagement() throws RemoteException{
            this.playerManagement = skeleton.getPlayerManagement();
            System.out.println("We got Player Management");
    }

    public IPlayerManagement getPlayerManagement(){
        return playerManagement;
    }

    public IPlayer getThePlayer() throws RemoteException {
        if(playerManagement == null){
            setPlayerManagement();
        }
        if(thePlayer == null){
            System.out.println("We tried getting the Player.");
            this.thePlayer = playerManagement.getPlayer("The Player");
        }
        return this.thePlayer;
    }

    public int getMinimalBetAllowed(){
        return minimalBetAllowed;
    }

    public void decreaseFundsBy(int amount) throws RemoteException{
        thePlayer.decreaseFundsBy(amount);
    }

    public void dealCards(){
        System.out.println("We would love to hand out some cards now.");
    }

    //TODO:REMOVE
    public void exitApp() throws RemoteException{
        if(skeleton == null){
            setSkeleton();
            System.out.println("yep");
        }
        System.out.println("mhm");
        skeleton.exitApp();
    }

    public void fold() throws RemoteException{
        thePlayer.fold();
    }

    public int getLatestPlacedBid() throws RemoteException{
        return skeleton.getLatestPlacedBid();
    }
}
