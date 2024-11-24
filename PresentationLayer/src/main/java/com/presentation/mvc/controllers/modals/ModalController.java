package com.presentation.mvc.controllers.modals;

import com.presentation.mvc.controllers.Controller;
import javafx.stage.Stage;

//modal can get a result and close the modal stage
public abstract class ModalController extends Controller{
    private Stage stage;
    private Object result;

    public void close() {
        stage.close();
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public Stage getStage() {
        return stage;
    }
    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
