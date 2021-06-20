package server.table;

import server.player.PlayerManagement;
import server.card.Card;

import java.util.ArrayList;

public class PokerTable {

    private static PokerTable instance;
    private ArrayList<Pot> potsInPlay;
    private ArrayList<Card> cardsOnTable;

    private PokerTable(){
        potsInPlay.add(new Pot(PlayerManagement.getInstance().getAllPlayers()));
    }

    public static PokerTable getInstance(){
        if(instance == null){
            instance = new PokerTable();
        }
        return instance;
    }

    public void addPotToTable(Pot pot){
        potsInPlay.add(pot);
        poolAllPossiblePots();
    }

    private void poolAllPossiblePots(){
        for(int i = potsInPlay.size(); i > 0; i--) {
            Pot currentPot = potsInPlay.get(1);
            Pot previousPot = potsInPlay.get(i-1);
//            if (canBePooled(currentPot, previousPot))
            if(currentPot.equals(previousPot)){
                previousPot.increasPotBy(currentPot.getAmountOfWinningsInPot());
                potsInPlay.remove(currentPot);
            }
        }
    }

//    private boolean canBePooled(Pot potOne, Pot potTwo){
//        if(potOne.getPlayersEligibleForWinnings().equals(potTwo.getPlayersEligibleForWinnings())){
//            return true;
//        }
//        return false;
//    }

/*THIS INSTEAD HAPPENS IN SINGLE ROUND
*
*    public void addPotToTable(ArrayList<Player> eligiblePlayers){
*        potsInPlay.add(new Pot(eligiblePlayers));
*    }
*/
    public void addCardToTable(Card card){
        cardsOnTable.add(card);
    }

    public ArrayList<Pot> getAllPotsInPlay(){
        return potsInPlay;
    }


}
