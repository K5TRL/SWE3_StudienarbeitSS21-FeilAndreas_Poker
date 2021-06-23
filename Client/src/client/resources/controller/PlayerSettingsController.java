package client.resources.controller;

import client.ClientStub;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class PlayerSettingsController extends ViewController{

    @FXML
    private TextField tfBuyIn;
    @FXML
    private Button btnAccept;
    @FXML
    private Button btnAbort;

    protected PlayerSettingsController(IViewLoader viewLoader, String fxmlPath) {
        super(viewLoader, fxmlPath);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setButtons();
            setTextField();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void setTextField() throws RemoteException {
        tfBuyIn.setText(""+ClientStub.getInstance().getBuyIn());
    }

    private void setButtons() throws RemoteException {
        setAcceptButton();
        setAbortButton();
    }

    private void setAcceptButton() throws RemoteException {
        btnAccept.setOnAction(actionEvent -> {
            try {
                ClientStub.getInstance().setBuyIn(Integer.parseInt(tfBuyIn.getText()));
                getViewLoader().loadMainMenu();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

    private void setAbortButton() {
        btnAbort.setOnAction(actionEvent -> getViewLoader().loadMainMenu());
    }
}
