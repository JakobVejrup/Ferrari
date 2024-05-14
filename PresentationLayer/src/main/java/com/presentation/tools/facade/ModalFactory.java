package com.presentation.tools.facade;

import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.model.entities.Employee;
import com.presentation.App;
import com.presentation.mvc.controllers.employees.modals.CreateEmployeeController;
import com.presentation.mvc.controllers.employees.modals.UpdateEmployeeController;
import com.presentation.mvc.controllers.modals.ModalController;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ModalFactory implements ModalSetup {
    private Stage mainStage;

    public ModalFactory(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void openModal(Request request) {
        try {
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            ModalController controller = getController(request, stage);
            Scene scene = new Scene(controller.getView(), Color.TRANSPARENT);
            scene.getStylesheets().add(App.class.getResource("stylesheet1.css").toExternalForm());
            controller.getView().getStyleClass().add("Modal");
            stage.setScene(scene);
            stage.initOwner(mainStage);
            stage.showAndWait();
            if(request.getSetter() != null)
                request.getSetter().action(controller.getResult());
        } 
        catch (Exception e) {
        }
    }
    private ModalController getController(Request request, Stage stage) {
        return switch (request.getType()) {
            case Employee -> {
                if (request.getCrud() == CRUDType.Create)
                    yield new CreateEmployeeController(stage);
                else
                    //updateEmployee should be made
                    yield new UpdateEmployeeController(stage, (Employee)request.getObject());
            }
                    //find its type, and then add ifs on what crudtype it is

                    default -> null;
        };
    }
}
