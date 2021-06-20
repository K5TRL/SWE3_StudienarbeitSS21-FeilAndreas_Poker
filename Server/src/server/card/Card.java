package server.card;

import remoteInterfaces.ICard;

import java.rmi.RemoteException;

public class Card implements ICard {
    private int value;
    private CardSuit suit;

    public Card(int value, CardSuit suit){
        this.value = value;
        this.suit = suit;
    }
    @Override
    public Card getCard() throws RemoteException {
        return this;
    }

    public int getValue(){
        return value;
    }

}
