package com.presentation.controllers.guiloading.modal;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ModalStage extends Stage {
    private Object result;
    public ModalStage() {
        super(StageStyle.TRANSPARENT);
        initModality(Modality.APPLICATION_MODAL);
    }

    public Object openModal() {
        showAndWait();
        return result;
    }
    public void setResult(Object value) {
        result = value;
    }
    public Object getResult() {
        return result;
    }
}
