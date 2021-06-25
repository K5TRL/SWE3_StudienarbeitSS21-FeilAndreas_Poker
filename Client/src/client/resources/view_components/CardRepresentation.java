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

    private final static String PATH_TO_IMAGES = "../images";

    @FXML
    private ImageView ivSuit;
    @FXML
    private Label lblValue;

    public CardRepresentation(){
        getStylesheets().add(this.getClass().getResource("CardRepresentation.css").toExternalForm());
    }

    public void setCard(ICard card){
        try{
            //String a = ""+this.getClass().getResource(PATH_TO_IMAGES+"/cardFaces/"+card.getSuit()+card.getValue()+".png");
            //System.out.println(a);
            this.setStyle(getStyle() + "-fx-background-image: url("+this.getClass().getResource(PATH_TO_IMAGES+"/cardFaces/"+card.getSuit()+card.getValue()+".png").toExternalForm()+");");
        }
        catch (Exception e){

        }
    }

    public void obscure(){
        this.setStyle(getStyle() + "-fx-background-image: url("+this.getClass().getResource(PATH_TO_IMAGES+"/cardBack.png").toExternalForm()+");");
    }

}
