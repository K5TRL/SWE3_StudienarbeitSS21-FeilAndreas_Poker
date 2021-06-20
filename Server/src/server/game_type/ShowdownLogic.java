package server.game_type;

import server.player.Player;
import server.player.PlayerManagement;
import server.GameLogic;
import server.table.PokerTable;
import server.table.Pot;

import java.util.LinkedList;

//FOR VALIDATION OF HANDS, DETERMINING WHICH HAND IS THE HIGHEST POSSIBLE FOR EACH PLAYER STILL IN PLAY + DETERMINING WHICH PLAYER WINS WHICH POT
public class ShowdownLogic {
    private static ShowdownLogic instance;
    private LinkedList<Player> hierarchyOfPlayers;
    private ShowdownLogic(){

    }
    public static ShowdownLogic getInstance(){
        if(instance == null){
            instance = new ShowdownLogic();
        }
        return instance;
    }

    /*goes through all pots first
    * then goes through the hierarchy we set
    * then checks if the person with the current highest hierarchy is eligible to win the pot
    * if he is eligible, he gets all the winnings in the pot.
    */
    public void payoutAllPots(){
        for(Pot pot : PokerTable.getInstance().getAllPotsInPlay()){
            for(Player player : hierarchyOfPlayers){
                if(pot.getPlayersEligibleForWinnings().contains(player)){
                    pot.payoutTo(player);
                }
            }
        }
        GameLogic.getInstance().prepareNewRound();
    }

    public void createHierarchy(){
        //TODO: REPLACE FOR ACTUAL HIERARCHY BUILDING
        for(Player player : PlayerManagement.getInstance().getAllPlayers()){
            hierarchyOfPlayers.add(player);
        }
        sortForHierarchy();
    }
    //TODO: SORTING
    private void sortForHierarchy(){
        for(int i = 1; i<hierarchyOfPlayers.size(); i++){
            Player p1 = hierarchyOfPlayers.get(i);
            int key = hierarchyOfPlayers.get(i).getHandValue();
            int j = i-1;
            while((i>-1) && (hierarchyOfPlayers.get(j).getHandValue() > key)){
                hierarchyOfPlayers.set(j+1, hierarchyOfPlayers.get(j));
                j--;
            }
        }
    }

    private void evaluateHand(Player player){
        //ArrayList<Card> eligibleCards = player.getPocketCards();

    }
}