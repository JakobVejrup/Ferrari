package com.presentation.mvc.controllers.employees;

import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.entities.Employee;
import com.model.threads.Validation;
import com.presentation.mvc.controllers.Controller;
import com.presentation.mvc.models.employees.EmployeeModel;
import com.presentation.mvc.views.employee.modals.CreateEmployeeView;
import com.presentation.tools.alert.Alerter;
import com.presentation.tools.facade.Facade;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class AccountController implements Controller{
    private CreateEmployeeView view;
    private EmployeeModel model;

    public AccountController() {
        model = new EmployeeModel(Facade.getInstance().getLoggedIn());

        Button updateButton = new Button("Opdater dig");
        updateButton.setOnAction(this::updateSelf);

        view = new CreateEmployeeView(model);
        view.addButtons(updateButton);
    }



    public void updateSelf(ActionEvent event) {
        ServiceSingleton.getInstance().query(new Request(ServiceType.Employee, CRUDType.UpdateSelf,
                model.getEmployee(),
                (update) -> {
                    if(update != null) 
                        Facade.getInstance().getLoggedIn().copy((Employee)update);
                },
                new Validation(
                    (request) -> {
                        Validation validation = ((Request) request).getValidation();
                        Platform.runLater(
                            () -> Alerter.information("Forkerte data", validation.getMessages())
                        );
                    }
                )
            )
        );
    }

    @Override
    public Pane getView() {
        return view;
    }
}
