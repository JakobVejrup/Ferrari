package com.presentation.controllers.guiloading.modal;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

//StageHandler
public abstract class ModalController extends Pane {
    private ModalStage stage;
    public ModalController(ModalStage stage) {
        this.stage = stage;
    }
    protected void cancel() {
        stage.close();
    }
    protected void accept(Object result) {
        stage.setResult(result);
        stage.close();
    }
}
