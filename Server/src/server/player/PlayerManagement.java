package server.player;

import java.util.ArrayList;

public class PlayerManagement {

    private ArrayList<Player> allPlayers;
    private static PlayerManagement instance;
    private Player dealer;
    private Player bigBlind;
    private Player smallBlind;
    private Player currentPlayerBetting;

    private PlayerManagement(){

    }
    public static PlayerManagement getInstance(){
        if(instance==null){
            instance = new PlayerManagement();
        }
        return instance;
    }

    public void addPlayer(Player player){
        allPlayers.add(player);
    }

    public ArrayList<Player> getAllPlayers(){
        return allPlayers;
    }

    public void switchToNextDealer(){
        dealer = allPlayers.get((allPlayers.indexOf(dealer)+1)%allPlayers.size());
        bigBlind = allPlayers.get((allPlayers.indexOf(bigBlind)+1)%allPlayers.size());
        smallBlind = allPlayers.get((allPlayers.indexOf(smallBlind)+1)% allPlayers.size());
    }



}
