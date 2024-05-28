package com.presentation.mvc.controllers.customers.modals;

import java.util.List;

import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.entities.City;
import com.model.threads.Validation;
import com.presentation.mvc.controllers.modals.ModalController;
import com.presentation.mvc.models.customer.CustomerModel;
import com.presentation.mvc.views.customer.modals.CustomerBaseView;
import com.presentation.tools.alert.Alerter;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CreateCustomerController extends ModalController{
    private CustomerModel model;
    private CustomerBaseView view;
    public CreateCustomerController(){
        model = new CustomerModel();
        Button createButton = new Button("Lav kunde");
        createButton.setOnAction(this::create);

        Button cancelButton = new Button("Fortryd");
        cancelButton.setOnAction(this::decline);
        
        ObjectProperty<ObservableList<City>> listProperty = new SimpleObjectProperty<>(FXCollections.observableArrayList());
        view = new CustomerBaseView(model, listProperty);
        view.addButtons(createButton, cancelButton);
        ServiceSingleton.getInstance().query(new Request(ServiceType.City, CRUDType.ReadAll,
            null,
            (cities) -> {
                ObservableList<City> obs = FXCollections.observableArrayList();
                for(City c : (List<City>)cities)
                    obs.add(c);
                listProperty.set(obs);
            })
        );    

        
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
                        ((CustomerModel)newCustomer).unbindAll();
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
