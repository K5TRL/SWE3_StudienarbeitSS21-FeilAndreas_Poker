package server.card;

import remoteInterfaces.ICard;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Card extends UnicastRemoteObject implements ICard {
    private int value;
    private String suit;

    public Card(int value, CardSuit suit) throws RemoteException {
        super();
        this.value = value;
        this.suit = suit.toString();
    }
    @Override
    public int getValue(){
        return value;
    }
    @Override
    public String getSuit(){return suit;}

}
