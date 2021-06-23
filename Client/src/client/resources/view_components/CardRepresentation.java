package client.resources.view_components;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import remoteInterfaces.ICard;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class CardRepresentation extends HBox implements Initializable {

    @FXML
    private ImageView ivSuit;
    @FXML
    private Label lblValue;

    private final ICard card;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setSuitVisuals();
        setValueVisuals();
    }

    public CardRepresentation(ICard card){
        this.card = card;
    }

    private void setLabels() {
    }

    private void setSuitVisuals() {

    }

    private void setValueVisuals() {

    }

}
