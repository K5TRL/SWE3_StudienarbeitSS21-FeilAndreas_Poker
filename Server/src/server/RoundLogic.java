package server;

public class RoundLogic {
//    private Player currentDealer;
//    private Player smallBlind;
//    private Player bigBlind;

    private int currentBettingRound;

    private static RoundLogic instance;

    private RoundLogic(){
        resetBettingRoundForNewGame();
    }
    public static RoundLogic getInstance(){
        if(instance == null) instance = new RoundLogic();
        return instance;
    }

    public int getCurrentBettingRound(){
        return currentBettingRound;
    }

    public void resetBettingRoundForNewGame(){
        currentBettingRound = 0;
    }

    public void moveToNextRound(){
        if(currentBettingRound < GameLogic.getInstance().getCurrentGameBeingPlayed().getAmountOfBettingRoundsAllowedPerGame()){
            currentBettingRound++;
            GameLogic.getInstance().prepareNewRound();
            //GameLogic.getInstance().getCurrentGameBeingPlayed().moveToNextRound();
        }
    }

}
