package client.resources.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayerActionsVBoxController extends VBox implements Initializable{
    @FXML
    private Button btnFold;
    @FXML
    private Button btnCall;
    @FXML
    private Button btnRaise;
    @FXML
    private Button btnToMainMenu;
    @FXML
    private Label lblPlayerBet;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setButtons();
    }

    private void setButtons() {
        setPlayerActionButtons();
        setPlayerBetLabel();
        setMainMenuButton();
    }

    private void setMainMenuButton() {
    }

    private void setPlayerBetLabel() {
    }

    private void setPlayerActionButtons() {

    }
}
