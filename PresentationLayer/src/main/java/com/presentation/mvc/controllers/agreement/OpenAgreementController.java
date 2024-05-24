package com.presentation.mvc.controllers.agreement;

import com.presentation.mvc.views.agreement.OpenAgreementView;
import com.presentation.tools.facade.Facade;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.entities.Employee;
import com.model.entities.Vehicle;
import com.presentation.mvc.controllers.Controller;
import com.presentation.mvc.controllers.customers.SingleCustomerController;
import com.presentation.mvc.controllers.employee.SingleEmployeeController;
import com.presentation.mvc.controllers.employee.modals.CreateEmployeeController;
import com.presentation.mvc.controllers.employee.modals.SelectEmployeeController;
import com.presentation.mvc.controllers.vehicle.SingleVehicleController;
import com.presentation.mvc.controllers.vehicle.modals.SelectVehicleController;
import com.presentation.mvc.models.agreements.OpenAgreementsModel;
import com.presentation.mvc.models.table.RowModel;

public class OpenAgreementController extends Controller {
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
        setView(view);
        Button selectEmployee = new Button("Vælg sælger");
        selectEmployee.setOnAction(this::chooseEmployee);
        employeeController.addButtons(selectEmployee);
        Button selectVehicle = new Button("Vælg bil");
        selectVehicle.setOnAction(this::chooseVehicle);
        vehicleController.addButtons(selectVehicle);

        
    }
    public void chooseEmployee(ActionEvent event) {
        Object result = Facade.getInstance().openModalResult(new SelectEmployeeController());
        if(result != null) {
            employeeController.setModel((Employee)result);
            model.setEmployee((Employee)result);
        }
    }
    public void chooseVehicle(ActionEvent event) {
        Object result = Facade.getInstance().openModalResult(new SelectVehicleController());
        if(result != null) {
            vehicleController.setModel((Vehicle)result);
            model.setVehicle((Vehicle)result);
        }
    }

}
