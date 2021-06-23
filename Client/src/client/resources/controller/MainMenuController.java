package client.resources.controller;

import client.ClientStub;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController extends ViewController{

    @FXML
    private Button btnStartTexasHoldEm;
    @FXML
    private Button btnStartOmaha;
    @FXML
    private Button btnPlayerSettings;
    @FXML
    private Button btnQuitGame;


    protected MainMenuController(SceneController viewLoader, String fxmlPath) {
        super(viewLoader, fxmlPath);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setButtons();
    }

    private void setButtons() {
        setTextHoldEmButton();
        setOmahaButton();
        setPlayerSettingsButtons();
        setExitButton();
    }

    private void setPlayerSettingsButtons() {
        btnPlayerSettings.setOnAction(actionEvent -> getViewLoader().loadPlayerSettings());
    }


    private void setTextHoldEmButton() {
        btnStartTexasHoldEm.setOnAction(actionEvent -> {
            ClientStub.getInstance().startNewTexasHoldEmGame();
            getViewLoader().loadPlayerView();
        });
    }
    private void setOmahaButton() {
        btnStartOmaha.setDisable(true);
    }

    private void setExitButton(){
        btnQuitGame.setOnAction(actionEvent -> Platform.exit());
    }
}
