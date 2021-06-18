package server;

import java.util.ArrayList;

public class Pot {
    private ArrayList<Player> playersEligibleForWinnings = new ArrayList<Player>();
    private int amountOfWinningsInPot;

    public Pot(ArrayList playersEligibleForWinnings){
        this.playersEligibleForWinnings = playersEligibleForWinnings;
        amountOfWinningsInPot = 0;
    }

    public void increasPotBy(){

    }

    public void payOutPot(){
        for(Player player : playersEligibleForWinnings){
            if(true /*TODO: VALIDATION: IS PLAYER WITH HIGHEST HAND ELIGIBLE FOR THIS POT? IF NOT CONTINUE. QUESTION: WHERE DO WE VALIDATE A CHRONOLOGICAL ORDER OF PLAYERS BASED ON STRENGTH OF HAND?*/){
                player.addFunds(amountOfWinningsInPot);
                amountOfWinningsInPot = 0;
            }
        }
    }
}
