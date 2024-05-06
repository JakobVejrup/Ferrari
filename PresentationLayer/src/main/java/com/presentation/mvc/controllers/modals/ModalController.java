package com.presentation.mvc.controllers.modals;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

//StageHandler
public abstract class ModalController {
    private Stage stage;

    public ModalController(Stage stage) {
        this.stage = stage;
    }
    protected void close() {
        stage.close();
    }

    public abstract Pane getView();
}
