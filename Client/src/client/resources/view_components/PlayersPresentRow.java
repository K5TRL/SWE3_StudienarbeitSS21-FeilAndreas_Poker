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
                System.out.println("Tryint to set Player.");
                playerPresentRepresentation.setPlayer(player);
                System.out.println("Player hath been set.");
                getChildren().add(playerPresentRepresentation);
            }
        }
        catch (Exception e){

        }
    }
}
