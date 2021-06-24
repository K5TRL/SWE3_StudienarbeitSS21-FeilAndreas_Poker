package server;

import remoteInterfaces.IGameLogic;
import server.card.AllCards;
import server.card.Card;
import server.game_type.GameType;
import server.game_type.TexasHoldEm;
import server.player.Player;
import server.player.PlayerManagement;
import server.round.RoundLogic;
import server.settings.Blinds;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GameLogic extends UnicastRemoteObject implements IGameLogic {

    private int BUY_IN = 1000;
    private static GameLogic instance;
    private GameType currentGameBeingPlayed;

    private GameLogic() throws RemoteException {
        super();
        setCurrentGameBeingPlayedToTexasHoldEm();
    }
    public static GameLogic getInstance() throws RemoteException {
        if(instance==null){
            instance = new GameLogic();
        }
        return instance;
    }

    public void setCurrentGameBeingPlayedToTexasHoldEm(){
        currentGameBeingPlayed = new TexasHoldEm();
    }

    public GameType getCurrentGameBeingPlayed(){
        return currentGameBeingPlayed;
    }

    public void prepareNewGame() throws RemoteException {
        preparePlayersForNewGame();
        RoundLogic.getInstance().resetBettingRoundForNewGame();
        PlayerManagement.getInstance().setDealerAndBlindsForNewGame();
        prepareNewRound();
    }

    public void prepareNewRound() throws RemoteException {
        prepareAllCards();
        resetPlayersforNewRound();
        RoundLogic.getInstance().newRound();
        currentGameBeingPlayed.executeCurrentRoundRules();
    }

    private void prepareAllCards(){
        AllCards.getInstance().resetFullDeckOfCards();
        AllCards.getInstance().shuffleDeck();
    }

    private void resetPlayersforNewRound() throws RemoteException {
        PlayerManagement.getInstance().getAllPlayers().forEach(player -> {
            player.resetThisPlayerForNewRound();
        });
        //Has to be this way, since everyone gets one card at a time!
        for(int i = 0; i<currentGameBeingPlayed.getAmountOfCardsAllowedOnHand(); i++){
            for(Player player : PlayerManagement.getInstance().getAllPlayers()){
                Card card= AllCards.getInstance().getRandomCard();
                player.addCardToHand(card);
                System.out.println("Card\t"+card.getSuit()+card.getValue()+" added to Player:\t"+player.getName());
            }
        }
    }

    private void preparePlayersForNewGame() throws RemoteException {
        PlayerManagement.getInstance().getAllPlayers().forEach(player -> {
            player.setFunds(BUY_IN);
        });
    }

    public int getBuyIn() {
        return BUY_IN;
    }

    public void executeCurrentRoundRules() throws RemoteException {
        currentGameBeingPlayed.executeCurrentRoundRules();
    }


    //TODO: A LOT OF THESE SHOULD BE IN SERVER SKELETON INSTEAD.

    @Override
    public void startNewGame() throws RemoteException {
        System.out.println("Client has sent request to start new game!");
        if(!(PlayerManagement.getInstance().getAllPlayers().size() > 0)){
            Player thePlayer = new Player("The Player");
            PlayerManagement.getInstance().addPlayer(thePlayer);
            System.out.println("Created:\t"+thePlayer.getName());
            Player theNPC = new Player("The NPC");
            PlayerManagement.getInstance().addPlayer(theNPC);
            System.out.println("Created:\t"+theNPC.getName());
        }
        prepareNewGame();
    }

    @Override
    public void startNewRound() throws RemoteException {
        prepareNewRound();
    }

    @Override
    public void changeBuyIn(int amount) throws RemoteException {
        BUY_IN = amount;
        Blinds.getInstance().setBlinds();
    }

    @Override
    public int getMinimalBetAllowed(){
        int latestPlacedBid = RoundLogic.getInstance().getCurrentBettingRound().getLatestPlacedBid();
        int bigBlind = Blinds.getInstance().getBigBlindAmount();
        if(latestPlacedBid>bigBlind){
            return latestPlacedBid;
        }
        return bigBlind;
    }


}
