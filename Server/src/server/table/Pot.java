package server.table;

import remoteInterfaces.IPlayer;
import server.player.Player;

import java.util.ArrayList;

public class Pot {
    private ArrayList<Player> playersEligibleForWinnings = new ArrayList<Player>();
    private int amountOfWinningsInPot;

    public Pot(ArrayList playersEligibleForWinnings){
        this.playersEligibleForWinnings = new ArrayList<>();
        playersEligibleForWinnings.forEach(player -> this.playersEligibleForWinnings.add((Player)player));
        amountOfWinningsInPot = 0;
        checkIfPotContested();
    }

    public Pot(ArrayList playersEligibleForWinnings, int amountOfWinningsInPot){
        this.playersEligibleForWinnings = playersEligibleForWinnings;
        this.amountOfWinningsInPot = amountOfWinningsInPot;
        checkIfPotContested();
    }

    public int getAmountOfWinningsInPot(){
        return amountOfWinningsInPot;
    }

    //TODO: MOVE TO CORRECT PLACE IN CODE:
    private void checkIfPotContested(){
        if(playersEligibleForWinnings.size()==1){
            payoutTo(playersEligibleForWinnings.get(0));
        }
    }

    public void increasPotBy(int amount){
        amountOfWinningsInPot+=amount;
    }


    public ArrayList<Player> getPlayersEligibleForWinnings(){
        return playersEligibleForWinnings;
    }

    public void payoutTo(Player player){
        player.addFunds(amountOfWinningsInPot);
        System.out.println("Funds added to player:\t"+amountOfWinningsInPot);
        amountOfWinningsInPot = 0;
    }

    //IN CASE OF FOLD
    public void removePlayerEligitability(Player player){
        playersEligibleForWinnings.remove(player);
        checkIfPotContested();
    }
}
