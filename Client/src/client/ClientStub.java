package client;

import remoteInterfaces.ICard;
import remoteInterfaces.IPlayer;
import remoteInterfaces.IPlayerManagement;
import remoteInterfaces.IServerSkeleton;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

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
            if(skeleton == null){
                setSkeleton();
            }
            minimalBetAllowed = skeleton.getGameLogic().getMinimalBetAllowed();
            skeleton.getGameLogic().startNewGame();
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
    }

    public IPlayerManagement getPlayerManagement(){
        return playerManagement;
    }

    public IPlayer getThePlayer() throws RemoteException {
        if(playerManagement == null){
            setPlayerManagement();
        }
        if(thePlayer == null){
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

    //TODO:REMOVE, THIS IS A LAZY METHOD SO I DON'T HAVE TO MANUALLY SHUTDOWN THE SERVER EVERY TIME I TEST SOMETHING.
    public void exitApp() throws RemoteException{
        if(skeleton == null){
            setSkeleton();
        }
        skeleton.exitApp();
    }

    public void fold() throws RemoteException{
        thePlayer.fold();
    }

    public int getLatestPlacedBid() throws RemoteException{
        return skeleton.getLatestPlacedBid();
    }

    public void continueToNextPlayer() throws RemoteException{
        skeleton.continueToNextPlayer();
    }

    public ArrayList<ICard> getCommunityCards() throws RemoteException{
        return skeleton.getCardsOnTable();
    }

    public int getCurrentBettingRoundNumber() throws RemoteException{
        return skeleton.getRoundLogic().getCurrentBettingRoundNumber();
    }

    public void resetThePlayer() throws RemoteException{
        getThePlayer().resetBooleanFoldedForNewRound();
    }
}
