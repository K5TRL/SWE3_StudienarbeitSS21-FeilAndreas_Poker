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

    protected MainMenuController(SceneController viewLoader, String fxmlPath) {
        super(viewLoader, fxmlPath);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setExitButton();
    }

    private void setExitButton(){
        btnQuitGame.setOnAction(actionEvent -> Platform.exit());
    }
}
