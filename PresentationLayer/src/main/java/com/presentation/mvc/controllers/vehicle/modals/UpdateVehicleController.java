package com.presentation.mvc.controllers.vehicle.modals;

import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.entities.Vehicle;
import com.model.threads.Validation;
import com.presentation.mvc.controllers.modals.ModalController;
import com.presentation.mvc.models.vehicle.VehicleModel;
import com.presentation.tools.alert.Alerter;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UpdateVehicleController extends ModalController{

    private VehicleModel model;
    private Vehicle vehicle;
    private VehicleBaseView view;
    public UpdateVehicleController(Stage stage, Vehicle vehicle) {
        super(stage);
        this.vehicle = vehicle;
        model = new VehicleModel(vehicle);
        view = new VehicleBaseView(model);
        Button updateButton = new Button("Opdater Bil");
        updateButton.setOnAction(this::update);

        Button cancelButton = new Button("Fortryd");
        cancelButton.setOnAction(this::decline);

        view.addButtons(updateButton, cancelButton);
    }

    @Override
    public Pane getView() {
        return view;
    }
    public void update(ActionEvent event) {
        ServiceSingleton.getInstance().query(new Request(ServiceType.Vehicle, CRUDType.Update,
        model,
        (newVehicle) -> {
            if(newVehicle != null) {
                vehicle.copy((Vehicle)newVehicle);
                Platform.runLater(this::close);
            }
        },
        new Validation(
            (request) -> {
                Validation validation = ((Request) request).getValidation();
                Platform.runLater(
                    () -> Alerter.information("Forkert data", validation.getMessages())
                );
            }
        )
        )
        );
    }
    public void decline(ActionEvent event) {
        close();
    }
}
