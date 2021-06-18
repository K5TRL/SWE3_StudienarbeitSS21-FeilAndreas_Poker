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

    public void addPotToTable(ArrayList<Player> eligiblePlayers){
        potsInPlay.add(new Pot(eligiblePlayers));
    }

    public void addCardToTable(Card card){
        cardsOnTable.add(card);
    }


}
