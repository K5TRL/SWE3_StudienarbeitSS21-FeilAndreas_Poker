package server;

import java.util.ArrayList;

public class PlayerManagement {

    private ArrayList<Player> allPlayers;
    private static PlayerManagement instance;

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

}
