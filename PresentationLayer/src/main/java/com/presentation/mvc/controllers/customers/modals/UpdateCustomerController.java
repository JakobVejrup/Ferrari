package com.presentation.mvc.controllers.customers.modals;

import java.util.List;

import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.entities.City;
import com.model.entities.Customer;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

//magnus
public class UpdateCustomerController extends ModalController {
    private CustomerModel model;
    private Customer customer;
    private CustomerBaseView view;
    public UpdateCustomerController (Customer customer) {
        this.customer = customer;
        model = new CustomerModel(customer);

        Button updateButton = new Button("Opdater Kunde");
        updateButton.setOnAction(this::update);

        Button cancelButton = new Button("Fortryd");
        cancelButton.setOnAction(this::decline);
        ObjectProperty<ObservableList<City>> listProperty = new SimpleObjectProperty<>(FXCollections.observableArrayList());
        view = new CustomerBaseView(model, listProperty);
        view.addButtons(updateButton, cancelButton);

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
public void update(ActionEvent event) {
    ServiceSingleton.getInstance().query(new Request(ServiceType.Customer, CRUDType.Update,
            model,
            (newCustomer) -> {
                if(newCustomer != null) {
                    customer.copy((Customer)newCustomer);
                    Platform.runLater(this::close);
                }
            },
            new Validation(
                (request) -> {
                    Validation validation = ((Request) request).getValidation();
                    Platform.runLater(
                        () -> Alerter.information("Forkerte Oplysninger", validation.getMessages())
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
