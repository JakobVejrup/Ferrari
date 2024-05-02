package com.presentation.controllers.guiloading;

import com.model.threads.Request;
import com.presentation.controllers.guiloading.modal.ModalSetup;
import com.presentation.controllers.guiloading.view.ViewSetup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GuiFacade implements ModalSetup, ViewSetup {
    private static GuiFacade instance;
    private BorderPane mainPane;
    private Stage mainStage;
    private ModalSetup modal;
    private ViewSetup view;
    private GuiFacade() {}
    public static GuiFacade getInstance() {
        return instance == null ? instance = new GuiFacade() : instance;
    }
    public void setMainPane(BorderPane mainPane) {
        this.mainPane = mainPane;
    }
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setModal(ModalSetup modal) {
        this.modal = modal;
    }

    public void setView(ViewSetup view) {
        this.view = view;
    }

    @Override
    public Object openModal(Request request) {
        return modal == null ? null : modal.openModal(request);
    }

    @Override
    public void setCenter(String scene) {
        if(view != null)
            view.setCenter(scene);
    }

    @Override
    public void setTop(String scene) {
        if(view != null)
            view.setTop(scene);
    }

    @Override
    public void setLeft(String scene) {
        if(view != null)
            view.setLeft(scene);
    }

    @Override
    public void setRight(String scene) {
        if(view != null)
            view.setRight(scene);
    }
}
