package client.resources.view_components;

import javafx.scene.layout.HBox;
import remoteInterfaces.ICard;
import remoteInterfaces.IPlayer;

public class PlayerHandRow extends HBox {
    private IPlayer player;
    private boolean cardsNotShown = false;

    public PlayerHandRow(){
        getStylesheets().add(this.getClass().getResource("PlayerHandRow.css").toExternalForm());
    }

    public void setPlayer(IPlayer player){
        this.player = player;
        setHandRow();
        //this.setStyle(getStyle()+"-fx-background-color: hotpink");
    }

    private void setHandRow(){
        clearHand();
        try{
            for(ICard card: player.getPocketCards()){
                var cardRepresentation = new CardRepresentation();
                cardRepresentation.setCard(card);
                getChildren().add(cardRepresentation);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        if(cardsNotShown){
            //setClosedCards();
        }
    }

    private void setClosedCards() {
        getChildren().forEach(card ->{
            if(card.getClass().equals(CardRepresentation.class)){
                ((CardRepresentation) card).obscure();
            }
        });
    }

    public void setCardsToSmall(){
        getChildren().forEach(card ->{
            if(card.getClass().equals(CardRepresentation.class)){
                ((CardRepresentation) card).getStyleClass().add("small");
            }
        });
    }

    public void clearHand(){getChildren().clear();}
}
