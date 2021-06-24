package client.resources.controller;

import client.ClientStub;
import client.resources.view_components.PlayerHandRow;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class PlayerViewController extends ViewController{
    @FXML
    private Button btnFold;
    @FXML
    private Button btnCall;
    @FXML
    private Button btnRaise;
    @FXML
    private Button btnToMainMenu;
    @FXML
    private Button btnDealCards;
    @FXML
    private Label lblPlayerBet;
    @FXML
    private Slider sldrRaiseAmount;
    @FXML
    private Label lblPlayerName;
    @FXML
    private Label lblPlayerFunds;
    @FXML
    private PlayerHandRow playerHandRow;

    protected PlayerViewController(SceneController viewLoader, String fxmlPath) {
        super(viewLoader, fxmlPath);
    }
    //It doesn't actually matter what kind of single properties they are or what kind of change they're waiting for.
    //They merely tell the code on client-side "hey, we changed something! take an other look at what's on server for you to get!"
    private SimpleIntegerProperty remainingPlayerFunds;
    private SimpleBooleanProperty playerFolded;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        update();
    }

    private void update(){
        try {
            setThePlayer();
            setListeners();
            setPlayerBetTextField();
            setSlider();
            setLabels();
            setButtons();
            setHandCards();
            disableAllButtons();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void setHandCards() {
        try {
            playerHandRow.setPlayer(ClientStub.getInstance().getThePlayer());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setListeners() {
        setFundsListener();
        setFoldListener();
    }

    private void setFoldListener() {
        playerFolded.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                try {
                    disableAllButtons();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void disableAllButtons() throws RemoteException{
        boolean hasFolded = ClientStub.getInstance().getThePlayer().hasFolded();
        btnRaise.setDisable(hasFolded);
        btnCall.setDisable(hasFolded);
        btnFold.setDisable(hasFolded);
        btnDealCards.setDisable(!hasFolded);
    }

    private void setThePlayer() throws RemoteException {
        if(ClientStub.getInstance().getThePlayer() != null){
            remainingPlayerFunds = new SimpleIntegerProperty(ClientStub.getInstance().getThePlayer().getFunds());
            playerFolded = new SimpleBooleanProperty(ClientStub.getInstance().getThePlayer().hasFolded());
        }
        else getViewLoader().loadMainMenu();
    }

    private void setFundsListener() {
        remainingPlayerFunds.addListener(new ChangeListener<Number>(){
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1){
                    try{
                        setLabels();
                        setSlider();
                        setButtons();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
    }

    private void setLabels() throws RemoteException {
        lblPlayerName.setText(ClientStub.getInstance().getThePlayer().getName());
        lblPlayerFunds.setText(""+ClientStub.getInstance().getThePlayer().getFunds());
    }

    private void setSlider() throws RemoteException {
        setSliderListener();
        int minBetAllowed = ClientStub.getInstance().getMinimalBetAllowed();
        if(minBetAllowed>ClientStub.getInstance().getThePlayer().getFunds()){
            sldrRaiseAmount.setDisable(true);
        }
        sldrRaiseAmount.setMin(minBetAllowed);
        sldrRaiseAmount.setMax(ClientStub.getInstance().getThePlayer().getFunds());
    }

    private void setSliderListener() {
        sldrRaiseAmount.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {

                try {
                    setPlayerBetTextField();
                    setRaiseButton();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setButtons() throws RemoteException {
        setPlayerActionButtons();
        setDealCardsButton();
        setToMainMenuButton();
    }

    private void setDealCardsButton() {
        btnDealCards.setText("Deal Cards");
        btnDealCards.setOnAction(actionEvent -> {
            try{
                ClientStub.getInstance().startNewRound();
                setHandCards();
                disableAllButtons();
                update();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });
        try {
            //btnDealCards.setDisable(ClientStub.getInstance().getThePlayer().hasFolded());

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setPlayerActionButtons() throws RemoteException {
        setCallButton();
        setFoldButton();
        setRaiseButton();
    }
    private void setPlayerBetTextField() {
        String a = String.format("%.0f",(sldrRaiseAmount.getValue()/10));
        int b = Integer.parseInt(a);
        lblPlayerBet.setText(""+(b*10));
    }
    private void setToMainMenuButton() {
        btnToMainMenu.setText("Exit");
        btnToMainMenu.setOnAction(actionEvent -> getViewLoader().loadMainMenu());
    }
    private void setCallButton() {
        btnCall.setText("Check");
    }
    private void setFoldButton() {
        btnFold.setText("Fold");
        btnFold.setOnAction(actionEvent -> {
            try{
                ClientStub.getInstance().fold();
                playerFolded.set(!playerFolded.getValue().booleanValue());
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    private void setRaiseButton() throws RemoteException {
        btnRaise.setText("Bet");
        validateRaiseButtonText();

    }

    private void validateRaiseButtonText() throws RemoteException {
        if(Integer.parseInt(lblPlayerBet.getText())== ClientStub.getInstance().getThePlayer().getFunds()){
        if(Integer.parseInt(lblPlayerBet.getText())==0){
            btnRaise.setDisable(true);
        }
        else btnRaise.setText("All In");
    }
    else if(/*somebody has set a bet before this player in this round*/false){
        btnRaise.setText("Raise");
    }
        //PROBLEM: we need to somehow send this over to the server too
        btnRaise.setOnAction(actionEvent -> {
            try{
                int a = ClientStub.getInstance().getThePlayer().getFunds();
                ClientStub.getInstance().getThePlayer().decreaseFundsBy(Integer.parseInt(lblPlayerBet.getText()));
                remainingPlayerFunds.set(a - Integer.parseInt(lblPlayerBet.getText()));
                //ClientStub.getInstance().decreaseFundsBy();
                //thePlayer.decreaseFundsBy(Integer.parseInt(lblPlayerBet.getText()));
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });
    }

}
