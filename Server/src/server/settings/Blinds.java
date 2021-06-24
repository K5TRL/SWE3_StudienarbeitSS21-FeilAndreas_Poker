package server.settings;

import server.GameLogic;

public class Blinds {
    private static Blinds instance;
    private int smallBlind;
    private int bigBlind;

    private Blinds(){
        setBlinds();
    }

    public void setBlinds(){
        try{
            smallBlind = (int)(GameLogic.getInstance().getBuyIn()*0.025);
            bigBlind = smallBlind*2;
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Blinds getInstance(){
        if(instance==null){
            instance = new Blinds();
        }
        return instance;
    }

    public int getSmallBlindAmount(){
        return smallBlind;
    }

    public int getBigBlindAmount(){
        return bigBlind;
    }

    public void raise(){
        smallBlind = smallBlind*2;
        bigBlind = bigBlind*2;
    }
}
