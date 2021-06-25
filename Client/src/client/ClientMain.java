package client;

import client.resources.controller.SceneController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientMain extends Application {

    public static Stage primaryStage;

    public static void main(String[] args) throws InterruptedException, NotBoundException, RemoteException {
        System.out.println("Client boot successful.");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        ClientMain.primaryStage = stage;

        stage.setResizable(false);
        stage.setWidth(1920);
        stage.setHeight(1080);
        stage.setFullScreen(true);
        stage.setTitle("Poker");

        Scene scene = new Scene(new HBox());
        SceneController sceneController = new SceneController(scene);
        stage.setScene(scene);
        stage.show();
    }
}
