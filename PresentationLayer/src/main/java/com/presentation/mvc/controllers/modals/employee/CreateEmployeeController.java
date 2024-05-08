package com.presentation.mvc.controllers.modals.employee;

import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.threads.Validation;
import com.presentation.mvc.controllers.modals.ModalController;
import com.presentation.mvc.models.modals.employee.CreateEmployeeModel;
import com.presentation.mvc.views.modals.employee.CreateEmployeeView;
import com.presentation.tools.alert.Alerter;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CreateEmployeeController extends ModalController {
    private CreateEmployeeModel model;
    private CreateEmployeeView view;
    public CreateEmployeeController(Stage stage) {
        super(stage);

        model = new CreateEmployeeModel();
        // needs a eventhandler which is a funtional interface type
        view = new CreateEmployeeView(model, this::create);
    }

    @Override
    public Pane getView() {
        return view;
    }
    public void create(ActionEvent event) {
        ServiceSingleton.getInstance().query(new Request(ServiceType.Employee, CRUDType.Create,
                model.getEmployee(),
                (newEmployee) -> {
                    if(newEmployee != null) {
                        setResult(newEmployee);
                        Platform.runLater(this::close);
                    }
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


}
