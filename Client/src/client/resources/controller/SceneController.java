package client.resources.controller;

import client.ClientMain;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class SceneController implements IViewLoader{
    private final Scene scene;
    private final ViewController mainMenuController;
    private final ViewController playerViewController;

    public SceneController(Scene scene){
        this.scene = scene;
        //scene = ClientMain.getPrimaryStage().getScene();
        scene.setFill(Color.HOTPINK);
        mainMenuController = new MainMenuController(this, "../fxml/MainMenu.fxml");
        playerViewController = new PlayerViewController(this, "../fxml/PlayerView.fxml");
        loadMainMenu();
        //loadPlayerView();
    }

    public void loadControllerAndUpdateScene(ViewController controller){
        loadFXML(controller);
    }

    private void loadFXML(ViewController controller) {
        String fxmlPath = controller.getFxmlPath();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        fxmlLoader.setController(controller);
        try{
            Parent parent = fxmlLoader.load();
            //parent.setOpacity(0);
            scene.setRoot(parent);
        }
        catch (Exception e){
            e.printStackTrace();
            System.err.println("FXML-File not found in\t"+controller.getFxmlPath());
        }
        scene.getRoot().requestFocus();
    }

    @Override
    public void loadMainMenu() {loadControllerAndUpdateScene(mainMenuController);}

    @Override
    public void loadPlayerView() {loadControllerAndUpdateScene(playerViewController);}
}
