package client.resources.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController extends ViewController{

    @FXML
    private Button btnQuitGame;
    @FXML
    private Button btnStartTexasHoldEm;
    @FXML
    private Button btnStartOmaha;


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
        setExitButton();
    }



    private void setTextHoldEmButton() {
        btnStartTexasHoldEm.setOnAction(actionEvent -> getViewLoader().loadPlayerView());
    }
    private void setOmahaButton() {
        btnStartOmaha.setDisable(true);
    }

    private void setExitButton(){
        btnQuitGame.setOnAction(actionEvent -> Platform.exit());
    }
}
