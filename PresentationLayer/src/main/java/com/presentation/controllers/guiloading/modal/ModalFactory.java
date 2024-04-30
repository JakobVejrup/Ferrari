package com.presentation.controllers.guiloading.modal;

import com.logic.handlers.Request;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ModalFactory implements ModalSetup {
    private Stage mainStage;

    public ModalFactory(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public Object openModal(Request request) {
        try {
            ModalStage stage = new ModalStage();
            ModalController controller = getController(stage, request);
            Scene scene = new Scene(controller, Color.TRANSPARENT);
            stage.setScene(scene);
            stage.initOwner(mainStage);
            return stage.openModal();
        } catch (Exception e) {
            return null;
        }
    }
    private ModalController getController(ModalStage stage, Request request) {
        return switch (request.getType()) {
            //find its type, and then add ifs on what crudtype it is

            default -> null;
        };
    }
}
