package com.presentation.tools.facade;

import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.model.entities.Employee;
import com.presentation.App;
import com.presentation.mvc.controllers.employee.modals.CreateEmployeeController;
import com.presentation.mvc.controllers.employee.modals.UpdateEmployeeController;
import com.presentation.mvc.controllers.modals.ModalController;
import com.presentation.tools.ScreenWatcher;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
//anders
public class ModalSetter implements ModalSetup {
    private Stage mainStage;
    private double x;
    private double y;

    public ModalSetter(Stage mainStage) {
        this.mainStage = mainStage;
    }
    public void openModal(ModalController controller) {
        try {
            Stage stage = new Stage();
            controller.setStage(stage);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            Scene scene = new Scene(controller.getView(), Color.TRANSPARENT);
            scene.getStylesheets().add(App.class.getResource("stylesheet1.css").toExternalForm());
            controller.getView().getStyleClass().add("Modal");
            stage.setScene(scene);
            ScreenWatcher.getInstance().setStage(stage);
            stage.initOwner(mainStage);
            scene.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    x = event.getSceneX();
                    y = event.getSceneY();
                }
            });
            scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                }
            });
            stage.showAndWait();
            ScreenWatcher.getInstance().setStage(mainStage);

        } 
        catch (Exception e) {
        }
    }
    public Object openModalResult(ModalController controller) {
        try {
            openModal(controller);
            return controller.getResult();
        } 
        catch (Exception e) {
            return null;
        }
    }

}
