package com.presentation.mvc.controllers.modals.employee;

import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.presentation.mvc.controllers.modals.ModalController;
import com.presentation.mvc.models.modals.employee.CreateEmployeeModel;
import com.presentation.mvc.views.modals.employee.CreateEmployeeView;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
        ServiceSingleton.getInstance().query(new Request(ServiceType.Employee, CRUDType.Create, true,
                model.getEmployee(), 
                (newEmployee) -> {
                    if (newEmployee != null) {
                        Platform.runLater(
                            () -> close()
                        );
                    }
                }
            )
        );

    }


}
