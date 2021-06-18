package client;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class SceneController implements IViewLoader{

    private final Scene scene;
    private static final SceneController instance;

    private final ViewController mainMenuController;

    public SceneController(Scene scene) {
        this.scene = scene;
        mainMenuController = new MainMenuController(this, "resources/fxml/MainMenu.fxml");
        loadMainMenu();
    }

    @Override
    public void loadMainMenu() {
        loadControllerAndUpdateScene(mainMenuController);
    }

    @Override
    public void loadPlayerSettings() {

    }

    @Override
    public void loadPlayerView() {

    }

    @Override
    public void loadBirdCardSelect() {

    }


    private void loadControllerAndUpdateScene(ViewController controller) {
        loadFXML(controller);
    }

    private void loadFXML(ViewController controller) {
        String fxmlPath = controller.getFxmlPath();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        fxmlLoader.setController(controller);
        try {
            Parent parent = fxmlLoader.load();
            parent.setOpacity(0);
            scene.setRoot(parent);
            System.out.println("bing");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("FXML-File not found " + controller.getFxmlPath());
        }
        scene.getRoot().requestFocus();
    }
}
