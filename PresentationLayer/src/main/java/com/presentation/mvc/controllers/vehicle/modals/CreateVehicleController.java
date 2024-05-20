package com.presentation.mvc.controllers.vehicle.modals;

import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.threads.Validation;
import com.presentation.mvc.controllers.modals.ModalController;
import com.presentation.mvc.controllers.vehicle.VehicleController;
import com.presentation.mvc.models.vehicle.VehicleModel;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class CreateVehicleController extends ModalController{

    private VehicleController model;
    private CreateVehicleBaseView view;
    public CreateVehicleController(Stage stage) {
        super (stage);
        model = new VehicleModel();
        Button createButton = new Button("Opret bil");
        createButton.setOnAction(this::create);
        Button cancelButton = new Button("Fortryd");
        view.addButtons(createButton, cancelButton);
    }

    @Override
    public Pane getView() {
        return view;
    }
    
    public void create(ActionEvent event) {
        ServiceSingleton.getInstance().query(new Request(ServiceType.Vehicle, CRUDType.Create,
        model,
        (newVehicle) -> {
            if(newVehicle != null) {
                setResult(newVehicle);
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
    );
    }
    public void decline(ActionEvent event) {
        close();
    }
}
