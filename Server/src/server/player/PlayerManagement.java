package server.player;

import remoteInterfaces.IPlayer;
import remoteInterfaces.IPlayerManagement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class PlayerManagement extends UnicastRemoteObject implements IPlayerManagement {

    private ArrayList<Player> allPlayers = new ArrayList<>();
    private static PlayerManagement instance;
    private Player dealer;
    private Player bigBlind;
    private Player smallBlind;
    private Player currentPlayerBetting;

    private PlayerManagement() throws RemoteException {
        super();
    }
    public static PlayerManagement getInstance() throws RemoteException {
        if(instance==null){
            instance = new PlayerManagement();
        }
        return instance;
    }

    public void setDealerAndBlindsForNewGame(){
        dealer = allPlayers.get(((int)Math.random()* allPlayers.size())% allPlayers.size());
        smallBlind = allPlayers.get((allPlayers.indexOf(dealer)+1)% allPlayers.size());
        bigBlind = allPlayers.get((allPlayers.indexOf(dealer)+2)% allPlayers.size());
    }

    public void addPlayer(Player player){
        allPlayers.add(player);
    }

    public ArrayList<Player> getAllPlayers(){
        return allPlayers;
    }

    public ArrayList<IPlayer> getAllIPlayers(){
        ArrayList<IPlayer> iPlayers = new ArrayList<>();
        allPlayers.forEach(player -> iPlayers.add(player));
        return iPlayers;
    }

    public void switchToNextDealer(){
        dealer = allPlayers.get((allPlayers.indexOf(dealer)+1)%allPlayers.size());
        bigBlind = allPlayers.get((allPlayers.indexOf(bigBlind)+1)%allPlayers.size());
        smallBlind = allPlayers.get((allPlayers.indexOf(smallBlind)+1)% allPlayers.size());
    }
    public Player getSmallBlindPlayer(){
        return smallBlind;
    }
    public Player getBigBlindPlayer(){
        return bigBlind;
    }

    @Override
    public IPlayer getPlayer(String name) throws RemoteException {
        return allPlayers.stream().filter(player -> player.getName().equals(name)).findFirst().orElse(null);
    }
}
