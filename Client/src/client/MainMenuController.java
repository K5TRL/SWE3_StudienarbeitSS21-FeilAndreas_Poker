package client;

import javafx.application.Platform;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController extends ViewController{

    public MainMenuController(IViewLoader viewLoader, String fxmlPath) {
        super(viewLoader, fxmlPath);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void startTexasHoldEmGame(){

    }

    public void closeApp(){
        Platform.exit();
    }

}
