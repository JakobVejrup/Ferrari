package com.presentation.mvc.controllers.vehicle;

import com.model.entities.Employee;
import com.model.entities.Vehicle;
import com.presentation.mvc.controllers.Controller;
import com.presentation.mvc.models.vehicle.VehicleModel;
import com.presentation.mvc.views.employee.SingleEmployeeView;
import com.presentation.mvc.views.vehicle.SingleVehicleView;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class SingleVehicleController extends Controller {
    private Vehicle model;
    private SingleVehicleView view;

    public SingleVehicleController(Vehicle model) {
        this.model = model;
        view = new SingleVehicleView(model);
        setView(view);
    }
    public void setModel(Vehicle model) {
        view.setModel(model);
    }
    public void addButtons(Button... buttons) {
        view.addButtons(buttons);
    }
}
