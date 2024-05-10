package com.presentation.mvc.controllers.modals;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

//StageHandler
public abstract class ModalController {
    private Stage stage;
    private Object result;

    public ModalController(Stage stage) {
        this.stage = stage;
    }
    public ModalController(Stage stage, Object update) {
        this.stage = stage;
    }
    protected void close() {
        stage.close();
    }

    public abstract Pane getView();

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
