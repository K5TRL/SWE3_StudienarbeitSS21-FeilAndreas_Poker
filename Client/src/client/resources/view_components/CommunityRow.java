package client.resources.view_components;

import client.ClientStub;
import remoteInterfaces.ICard;

import java.util.ArrayList;

public class CommunityRow extends PlayerHandRow {

    public CommunityRow(){
        getStylesheets().add(this.getClass().getResource("CommunityRow.css").toExternalForm());
        var stackRepresentation = new CardRepresentation();
        stackRepresentation.setStyle(getStyle() + "-fx-effect: dropshadow(gaussian,black,5,0,5,5);");
        stackRepresentation.obscure();
        getChildren().add(stackRepresentation);
    }

    public void setCommunityCards(ArrayList<ICard> cards){
        try{
            for(ICard card : ClientStub.getInstance().getCommunityCards()){
                var cardRepresentation = new CardRepresentation();
                cardRepresentation.setCard(card);
                getChildren().add(cardRepresentation);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
