package com.presentation.mvc.controllers.customers.modals;

import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.threads.Validation;
import com.presentation.mvc.controllers.modals.ModalController;
import com.presentation.mvc.models.customer.CustomerModel;
import com.presentation.mvc.views.customer.modals.CreateCustomerView;
import com.presentation.mvc.views.customer.modals.CustomerBaseView;
import com.presentation.tools.alert.Alerter;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CreateCustomerController extends ModalController{
    private CustomerModel model;
    private CustomerBaseView cutomerView;
    private HBox view;
    public CreateCustomerController(){
        model = new CustomerModel();
        view = new HBox();
        Button createButton = new Button("Lav kunde");
        createButton.setOnAction(this::create);

        Button cancelButton = new Button("Fortryd");
        cancelButton.setOnAction(this::decline);
        cutomerView = new CustomerBaseView(model);
        cutomerView.addButtons(createButton, cancelButton);
        

        
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
