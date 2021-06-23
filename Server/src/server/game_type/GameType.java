package server.game_type;

import java.rmi.RemoteException;

public abstract class GameType {
    private final int amountOfCardsAllowedOnHand;
    private final String nameOfTheGame;
    private final int amountOfBettingRoundsAllowedPerGame;

    protected GameType(int amountOfCardsAllowedOnHand, String nameOfTheGame, int amountOfBettingRoundsAllowedPerGame){
        this.amountOfCardsAllowedOnHand = amountOfCardsAllowedOnHand;
        this.nameOfTheGame = nameOfTheGame;
        this.amountOfBettingRoundsAllowedPerGame = amountOfBettingRoundsAllowedPerGame;
    }

    public int getAmountOfCardsAllowedOnHand(){
        return amountOfCardsAllowedOnHand;
    }
    public String getNameOfTheGame(){
        return nameOfTheGame;
    }
    public int getAmountOfBettingRoundsAllowedPerGame(){
        return amountOfBettingRoundsAllowedPerGame;
    }
    public void executeCurrentRoundRules() throws RemoteException {}
    //public void moveToNextRound(){}

}
