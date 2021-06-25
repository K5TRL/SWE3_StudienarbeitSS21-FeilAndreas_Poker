package server.game_type;

import server.card.AllCards;
import server.player.PlayerManagement;
import server.settings.Blinds;
import server.card.Card;
import server.round.RoundLogic;
import server.table.PokerTable;

import java.rmi.RemoteException;
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

    public void executeCurrentRoundRules() throws RemoteException {
        switch (RoundLogic.getInstance().getCurrentBettingRoundNumber()){
            case 0:
                payBlinds();
                break;
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
                ShowdownLogic.getInstance().payoutAllPots();
                break;
        }
    }

    public void payBlinds() throws RemoteException {
        PlayerManagement.getInstance().getSmallBlindPlayer().bet(Blinds.getInstance().getSmallBlindAmount());
        PlayerManagement.getInstance().getBigBlindPlayer().bet(Blinds.getInstance().getBigBlindAmount());
        System.out.println("payBlinds");
    }
    //public void moveToNextRound(){}

    public void initiateFlop(){
        System.out.println("doFlop");
        AllCards.getInstance().burnCard();
        ArrayList<Card> list = new ArrayList();
        for(int i = 0; i<3; i++){
            list.add(AllCards.getInstance().getRandomCard());
        }
        moveCardsToTable(list);
    }

    public void initiateTurn(){
        System.out.println("makeTurn");
        AllCards.getInstance().burnCard();
        ArrayList<Card> list = new ArrayList();
        for(int i = 0; i<1; i++){
            list.add(AllCards.getInstance().getRandomCard());
        }
        moveCardsToTable(list);
    }

    public void initateRiver(){
        System.out.println("crossRiver");
        AllCards.getInstance().burnCard();
        ArrayList<Card> list = new ArrayList();
        for(int i = 0; i<1; i++){
            list.add(AllCards.getInstance().getRandomCard());
        }
        moveCardsToTable(list);
    }

    private void moveCardsToTable(ArrayList<Card> list) {
        for (Card card : list) {
            try {
                PokerTable.getInstance().addCardToTable(card);
                System.out.println("Community card added to table:\t"+card.getSuit()+card.getValue());
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
