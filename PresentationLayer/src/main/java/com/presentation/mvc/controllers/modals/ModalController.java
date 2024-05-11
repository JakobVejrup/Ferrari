package com.presentation.mvc.controllers.modals;
import com.presentation.mvc.controllers.Controller;
import javafx.stage.Stage;

//StageHandler
public abstract class ModalController implements Controller{
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

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
