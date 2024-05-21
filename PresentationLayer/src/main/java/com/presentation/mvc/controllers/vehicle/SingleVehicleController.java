package com.presentation.mvc.controllers.vehicle;

import com.model.entities.Employee;
import com.model.entities.Vehicle;
import com.presentation.mvc.controllers.Controller;
import com.presentation.mvc.models.vehicle.VehicleModel;
import com.presentation.mvc.views.employee.SingleEmployeeView;
import com.presentation.mvc.views.vehicle.SingleVehicleView;

import javafx.scene.layout.Pane;

public class SingleVehicleController implements Controller {
    private Vehicle model;
    private SingleVehicleView view;

    public SingleVehicleController(Vehicle model) {
        this.model = model;
        view = new SingleVehicleView(model);
    }
    public void setModel(Vehicle model) {
        view.setModel(model);
    }
    @Override
    public Pane getView() {
        return view;
    }
}
