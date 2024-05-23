package com.presentation.mvc.controllers.vehicle.modals;

import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.threads.Validation;
import com.presentation.mvc.controllers.modals.ModalController;
import com.presentation.mvc.controllers.vehicle.AllVehiclesController;
import com.presentation.mvc.models.vehicle.VehicleModel;
import com.presentation.mvc.views.vehicle.modals.VehicleView;
import com.presentation.tools.ImageFinder;
import com.presentation.tools.alert.Alerter;
import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CreateVehicleController extends ModalController{

    private VehicleModel model;
    private VehicleView view;
    public CreateVehicleController() {
        model = new VehicleModel();
        view = new VehicleView(model);
        Button createButton = new Button("Opret bil");
        createButton.setOnAction(this::create);
        Button cancelButton = new Button("Fortryd");
        cancelButton.setOnAction(this::decline);
        Button imageButton = new Button("Vælg Billede");
        imageButton.setOnAction(this::findImage);
        view.addButtons(createButton, imageButton, cancelButton);
    }

    @Override
    public Pane getView() {
        return view;
    }
    public void findImage(ActionEvent event) {
        byte[] image = ImageFinder.findImage((Stage)view.getScene().getWindow());
        if (image != null) 
            model.setImage(image);
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
    ));
    }
    public void decline(ActionEvent event) {
        close();
    }
}
