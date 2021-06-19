package server;

import server.Card.Card;

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
    }

    private void poolAllPossiblePots(){
        for(int i = 0; i < potsInPlay.size()-1; i++) {
            if (canBePooled(potsInPlay.get(i), potsInPlay.get(i++))) {

            }
        }
    }

    private boolean canBePooled(Pot potOne, Pot potTwo){
        //can this be correct?
        if(potOne.getPlayersEligibleForWinnings() == potTwo.getPlayersEligibleForWinnings()){
            return true;
        }
        return false;
    }

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
