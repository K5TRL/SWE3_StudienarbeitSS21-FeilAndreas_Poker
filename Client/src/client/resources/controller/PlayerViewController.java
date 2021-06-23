package client.resources.controller;

import client.ClientStub;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import remoteInterfaces.IPlayer;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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
    private Label lblPlayerBet;
    @FXML
    private Slider sldrRaiseAmount;
    @FXML
    private Label lblPlayerName;
    @FXML
    private Label lblPlayerFunds;

    protected PlayerViewController(SceneController viewLoader, String fxmlPath) {
        super(viewLoader, fxmlPath);
    }
    private IPlayer thePlayer;
    private SimpleIntegerProperty remainingPlayerFunds;

//    private IPlayer player = new IPlayer() {
//        private int funds = 1000;
//        private SimpleIntegerProperty fundsProperty;
//        private String name = "Andreas";
//
//        @Override
//        public SimpleIntegerProperty getFunds() {
//            fundsProperty = new SimpleIntegerProperty(funds);
//            fundsProperty.addListener(new ChangeListener<Number>(){
//                @Override
//                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1){
//                    setLabels();
//                    setSlider();
//                    setButtons();
//                }
//            });
//            return fundsProperty;
//        }
//
//        @Override
//        public String getName() {
//            return name;
//        }
//
//        @Override
//        public void decreaseFundsBy(int amount) {
//            //fundsProperty.set(funds-amount);
//            System.out.println("Amount:\t"+amount);
//            fundsProperty.set(funds-=amount);
//            System.out.println("Left:\t"+fundsProperty.getValue().intValue());
//        }
//    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setThePlayer();
            setPlayerBetTextField();
            setSlider();
            setLabels();
            setButtons();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
//        try {
//            removeThisMethod();
//        } catch (NotBoundException e) {
//            e.printStackTrace();
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
    }

    private void setThePlayer() throws RemoteException {
        if(ClientStub.getInstance().getThePlayer() != null){
            thePlayer= ClientStub.getInstance().getThePlayer();
            remainingPlayerFunds = new SimpleIntegerProperty(thePlayer.getFunds());
            setFundsListener();
        }
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

    private void removeThisMethod() throws NotBoundException, RemoteException {
        Registry registry = LocateRegistry.getRegistry(8090);
        this.thePlayer = (IPlayer) registry.lookup(IPlayer.class.getName());
    }

    private void setLabels() throws RemoteException {
        lblPlayerName.setText(thePlayer.getName());
        lblPlayerFunds.setText(""+remainingPlayerFunds.getValue().intValue());
    }

    private void setSlider() throws RemoteException {
        setSliderListener();
        sldrRaiseAmount.setMin(/*getMinAmountFromServer*/100);
        sldrRaiseAmount.setMax(remainingPlayerFunds.getValue().intValue());
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

    private void setAllIn(){
        btnRaise.setText("All In");
    }

    private void setButtons() throws RemoteException {
        setPlayerActionButtons();
        setToMainMenuButton();
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
    }

    private void setRaiseButton() throws RemoteException {
        btnRaise.setText("Bet");
        validateRaiseButtonText();

    }

    private void validateRaiseButtonText() throws RemoteException {
        if(Integer.parseInt(lblPlayerBet.getText())== remainingPlayerFunds.getValue().intValue()){
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
                int a = remainingPlayerFunds.getValue().intValue();
                remainingPlayerFunds.set(a - Integer.parseInt(lblPlayerBet.getText()));
                //thePlayer.decreaseFundsBy(Integer.parseInt(lblPlayerBet.getText()));
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });
    }

}
