package server.GameType;

public abstract class GameType {
    private final int amountOfCardsAllowedOnHand;
    private final String nameOfTheGame;

    protected GameType(int amountOfCardsAllowedOnHand, String nameOfTheGame){
        this.amountOfCardsAllowedOnHand = amountOfCardsAllowedOnHand;
        this.nameOfTheGame = nameOfTheGame;
    }

    public int getAmountOfCardsAllowedOnHand(){
        return amountOfCardsAllowedOnHand;
    }
    public String getNameOfTheGame(){
        return nameOfTheGame;
    }

}
