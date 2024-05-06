package com.presentation.tools.facade;

import com.logic.handlers.Request;

import com.logic.services.enums.CRUDType;
import com.presentation.mvc.controllers.modals.ModalController;
import com.presentation.mvc.controllers.modals.employee.CreateEmployeeController;
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
            Stage stage = new Stage();
            ModalController controller = getController(request, stage);
            Scene scene = new Scene(controller.getView(), Color.TRANSPARENT);
            stage.setScene(scene);
            stage.initOwner(mainStage);
            stage.showAndWait();
            return null;
        } catch (Exception e) {
            return null;
        }
    }
    private ModalController getController(Request request, Stage stage) {
        return switch (request.getType()) {
            case Employee -> {
                if (request.getCrud() == CRUDType.Create)
                    yield new CreateEmployeeController(stage);
                else
                    //updateEmployee should be made
                    yield new CreateEmployeeController(stage);
            }
                    //find its type, and then add ifs on what crudtype it is

                    default -> null;
        };
    }
}
