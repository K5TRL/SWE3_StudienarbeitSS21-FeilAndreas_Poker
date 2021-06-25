package client.resources.view_components;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import remoteInterfaces.ICard;
import remoteInterfaces.IPlayer;


public class PlayerPresentRepresentation extends VBox {

    private Label lblName = new Label();
    private Label lblFunds = new Label();
    private ImageView ivActionMadeThisTurn = new ImageView();
    private PlayerHandRow pocketCards = new PlayerHandRow();

    public PlayerPresentRepresentation(){
        getStylesheets().add(this.getClass().getResource("PlayerPresentRepresentation.css").toExternalForm());
    }

    public void setPlayer(IPlayer player){
        try{
            lblName.setText(player.getName());
            lblFunds.setText(""+player.getFunds());
            //TODO: ImageView should show a lable based on the action made this turn; would help to let players keep an eye on things.
            pocketCards.getStyleClass().add("small");
            pocketCards.setPlayer(player);
            pocketCards.setCardsToSmall();
            getChildren().addAll(lblName,lblFunds,pocketCards);
        }
        catch (Exception e){

        }
    }
}
