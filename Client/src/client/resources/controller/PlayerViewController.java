package client.resources.controller;

import client.ClientStub;
import client.resources.view_components.CommunityRow;
import client.resources.view_components.PlayerHandRow;
import client.resources.view_components.PlayersPresentRow;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

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
    private Button btnDealCardsAndShowdown;
    @FXML
    private Label lblPlayerBet;
    @FXML
    private Slider sldrRaiseAmount;
    @FXML
    private Label lblPlayerName;
    @FXML
    private Label lblPlayerFunds;
    @FXML
    private Label lblPot;
    @FXML
    private PlayerHandRow playerHandRow;
    @FXML
    private CommunityRow communityRow;
    @FXML
    private PlayersPresentRow playersPresent;

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
            disableAllButtons();
            setThePlayer();
            setListeners();
            setPlayerBetTextField();
            setSlider();
            setLabels();
            setButtons();
            setHandCards();
            setAllPlayersPresentInGame();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void setAllPlayersPresentInGame() {
        playersPresent.setPlayersPresent();
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
        boolean disable = false;
        if(ClientStub.getInstance().getCurrentBettingRoundNumber()>=3 || ClientStub.getInstance().getThePlayer().hasFolded()){
            disable = true;
        }
        btnRaise.setDisable(disable);
        btnCall.setDisable(disable);
        btnFold.setDisable(disable);
        btnDealCardsAndShowdown.setDisable(!disable);
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

    private void setDealCardsButton() throws RemoteException {
        if(ClientStub.getInstance().getCommunityCards().size() == 5){
            btnDealCardsAndShowdown.setText("Showdown");
            btnDealCardsAndShowdown.setOnAction(actionEvent -> {
                try {
                    continueToNextPlayer();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                update();
            });
        }
        else{
            btnDealCardsAndShowdown.setText("Deal Cards");
            btnDealCardsAndShowdown.setOnAction(actionEvent -> {
                try{
                    ClientStub.getInstance().startNewRound();
                    ClientStub.getInstance().resetThePlayer();
                    setHandCards();
                    disableAllButtons();
                    communityRow.setCommunityCards(ClientStub.getInstance().getCommunityCards());
                    update();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            });
        }

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
    private void setCallButton() {
        btnCall.setText("Check");
        btnCall.setOnAction(actionEvent -> {
            try {
                if(ClientStub.getInstance().getLatestPlacedBid() > 0){
                    btnCall.setText("Call");
                    ClientStub.getInstance().getThePlayer().decreaseFundsBy(ClientStub.getInstance().getLatestPlacedBid());
                }
                continueToNextPlayer();
                update();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    private void setFoldButton() {
        btnFold.setText("Fold");
        btnFold.setOnAction(actionEvent -> {
            try{
                ClientStub.getInstance().fold();
                playerFolded.set(!playerFolded.getValue().booleanValue());
                continueToNextPlayer();
                update();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    private void setRaiseButton() throws RemoteException {
        btnRaise.setText("Bet");
        validateRaiseButtonText();
        btnRaise.setOnAction(actionEvent -> {
            try{
                int a = ClientStub.getInstance().getThePlayer().getFunds();
                ClientStub.getInstance().getThePlayer().decreaseFundsBy(Integer.parseInt(lblPlayerBet.getText()));
                remainingPlayerFunds.set(a - Integer.parseInt(lblPlayerBet.getText()));
                continueToNextPlayer();
                update();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });
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

    private void validateRaiseButtonText() throws RemoteException {
        if(Integer.parseInt(lblPlayerBet.getText())== ClientStub.getInstance().getThePlayer().getFunds()){
            if(Integer.parseInt(lblPlayerBet.getText())==0){
                btnRaise.setDisable(true);
            }
            else btnRaise.setText("All In");
        }/*somebody has set a bet before this player in this round false*/
        else if(ClientStub.getInstance().getLatestPlacedBid() > 0){
            btnRaise.setText("Raise");

            btnCall.setText("Call");
        }
        //PROBLEM: we need to somehow send this over to the server too

    }

    private void continueToNextPlayer() throws RemoteException {
        ClientStub.getInstance().continueToNextPlayer();
        if(ClientStub.getInstance().getCommunityCards() != null){
            communityRow.setCommunityCards(ClientStub.getInstance().getCommunityCards());
        }
    }

}
