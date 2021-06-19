package server.GameType;

import server.AllCards;
import server.Card.Card;
import server.RoundLogic;

import java.util.ArrayList;

public class TexasHoldEm extends GameType{

    private static final int MAX_CARDS_ON_HAND = 2;
    private static final String NAME_OF_THE_GAME = "Texas Hold 'Em";
    private static final int MAX_BETTING_ROUNDS = 4;

    public TexasHoldEm() {
        super(MAX_CARDS_ON_HAND, NAME_OF_THE_GAME, MAX_BETTING_ROUNDS);
    }

    protected TexasHoldEm(int maxCardsOnHand, String nameOfTheGame, int maxBettingRounds) {
        super(maxCardsOnHand,nameOfTheGame,maxBettingRounds);
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
                payBlinds();
                break;
        }
    }

    public void payBlinds() {

    }
    //public void moveToNextRound(){}

    public ArrayList initiateFlop(){
        AllCards.getInstance().burnCard();
        ArrayList<Card> list = new ArrayList();
        for(int i = 0; i<3; i++){
            list.add(AllCards.getInstance().getRandomCard());
        }
        return list;
    }

    public ArrayList initiateTurn(){
        AllCards.getInstance().burnCard();
        ArrayList<Card> list = new ArrayList();
        for(int i = 0; i<1; i++){
            list.add(AllCards.getInstance().getRandomCard());
        }
        return list;
    }

    public ArrayList initateRiver(){
        AllCards.getInstance().burnCard();
        ArrayList<Card> list = new ArrayList();
        for(int i = 0; i<1; i++){
            list.add(AllCards.getInstance().getRandomCard());
        }
        return list;
    }
}
