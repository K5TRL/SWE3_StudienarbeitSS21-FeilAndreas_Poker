package client.resources.view_components;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import remoteInterfaces.ICard;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class CardRepresentation extends Label /*implements Initializable*/ {

    @FXML
    private ImageView ivSuit;
    @FXML
    private Label lblValue;

    public CardRepresentation(){
        getStylesheets().add(this.getClass().getResource("../css/CardRepresentation.css").toExternalForm());
    }

    public void setCard(ICard card){
        try{
            Image image = new Image("../images/"+card.getSuit());
            ivSuit.setImage(image);
            lblValue.setText(""+card.getValue());
        }
        catch (Exception e){
            
        }
    }

}
