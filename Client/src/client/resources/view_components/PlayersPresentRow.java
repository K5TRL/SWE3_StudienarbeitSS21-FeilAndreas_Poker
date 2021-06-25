package client.resources.view_components;

import client.ClientStub;
import javafx.scene.layout.HBox;
import remoteInterfaces.IPlayer;

public class PlayersPresentRow extends HBox {
    public PlayersPresentRow(){
        getStylesheets().add(this.getClass().getResource("PlayersPresentRow.css").toExternalForm());
    }

    public void setPlayersPresent(){
        getChildren().clear();
        try{
            for(IPlayer player : ClientStub.getInstance().getPlayerManagement().getAllIPlayers()){
                var playerPresentRepresentation = new PlayerPresentRepresentation();
                playerPresentRepresentation.setPlayer(player);
                getChildren().add(playerPresentRepresentation);
            }
        }
        catch (Exception e){

        }
    }
}
