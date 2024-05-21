package com.presentation.mvc.controllers.agreement;

import com.presentation.mvc.views.agreement.OpenAgreementView;
import com.presentation.mvc.controllers.Controller;
import com.presentation.mvc.controllers.customers.SingleCustomerController;
import com.presentation.mvc.controllers.employee.SingleEmployeeController;
import com.presentation.mvc.controllers.vehicle.SingleVehicleController;

import javafx.scene.layout.Pane;
import com.presentation.mvc.models.agreements.OpenAgreementsModel;

public class OpenAgreementController implements Controller {
    private OpenAgreementView view;
    private OpenAgreementsModel model;
    private SingleCustomerController customerController;
    private SingleEmployeeController employeeController;
    private SingleVehicleController vehicleController;
    public OpenAgreementController(OpenAgreementsModel model) {
        this.model = model;
        customerController = new SingleCustomerController(model.getCustomer());
        employeeController = new SingleEmployeeController(model.getEmployee());
        vehicleController = new SingleVehicleController(model.getVehicle());
        view = new OpenAgreementView(employeeController.getView(), customerController.getView(), vehicleController.getView(), model);

    }

    @Override
    public Pane getView() {
        return view;
    }

}
