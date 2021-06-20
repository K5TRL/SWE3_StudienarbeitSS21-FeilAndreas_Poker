package client.resources.controller;

import javafx.fxml.Initializable;

public abstract class ViewController implements Initializable {
    private final IViewLoader viewLoader;
    private final String fxmlPath;

    protected ViewController(IViewLoader viewLoader, String fxmlPath){
        this.viewLoader = viewLoader;
        this.fxmlPath = fxmlPath;
    }

    public String getFxmlPath(){
        return fxmlPath;
    }

    public IViewLoader getViewLoader(){
        return viewLoader;
    }
}
