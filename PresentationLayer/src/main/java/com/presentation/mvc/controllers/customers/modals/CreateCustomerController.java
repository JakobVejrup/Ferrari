package com.presentation.mvc.controllers.customers.modals;

import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.threads.Validation;
import com.presentation.mvc.controllers.modals.ModalController;
import com.presentation.mvc.models.customer.CustomerModel;
import com.presentation.mvc.views.customer.modals.CreateCustomerView;
import com.presentation.tools.alert.Alerter;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CreateCustomerController extends ModalController{
    private CustomerModel model;
    private CreateCustomerView view;
    public CreateCustomerController(){
        model = new CustomerModel();

        Button createButton = new Button("Lav kunde");
        createButton.setOnAction(this::create);

        Button cancelButton = new Button("Fortryd");
        cancelButton.setOnAction(this::decline);
        view = new CreateCustomerView(model);
        view.addButtons(createButton, cancelButton);
    }

    @Override
    public Pane getView() {
        return view;
    }
    public void create(ActionEvent event) {
        ServiceSingleton.getInstance().query(new Request(ServiceType.Customer, CRUDType.Create,
                model,
                (newCustomer) -> {
                    if(newCustomer != null) {
                        setResult(newCustomer);
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
    public void decline(ActionEvent event) {
        close();
    }
    
}
