package server.settings;

public class Blinds {
    private static Blinds instance;
    private int smallBlind;
    private int bigBlind;

    private Blinds(){
        smallBlind = 100;
        bigBlind = smallBlind*2;
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
