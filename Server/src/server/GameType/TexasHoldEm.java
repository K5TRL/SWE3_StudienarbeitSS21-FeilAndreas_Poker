package server.GameType;

import server.AllCards;
import server.Card.Card;
import server.RoundLogic;

import java.util.ArrayList;

public class TexasHoldEm extends GameType{

    public TexasHoldEm() {
        super(5, "Texas Hold 'Em", 4);
    }
    //private static final int AMOUNT_OF_ROUNDS = 3;

    public void executeCurrentRoundRules(){
        switch (RoundLogic.getInstance().getCurrentBettingRound()){
            case 1:
                initiateFlop();
                break;
            case 2:
                initiateTurn();
                break;
            case 3:
                initateRiver();
                break;
            default:
                break;
        }
    }
    //public void moveToNextRound(){}

    public ArrayList initiateFlop(){
        ArrayList<Card> list = new ArrayList();
        for(int i = 0; i<3; i++){
            list.add(AllCards.getInstance().getRandomCard());
        }
        return list;
    }

    public ArrayList initiateTurn(){
        ArrayList<Card> list = new ArrayList();
        for(int i = 0; i<1; i++){
            list.add(AllCards.getInstance().getRandomCard());
        }
        return list;
    }

    public ArrayList initateRiver(){
        ArrayList<Card> list = new ArrayList();
        for(int i = 0; i<1; i++){
            list.add(AllCards.getInstance().getRandomCard());
        }
        return list;
    }
}
