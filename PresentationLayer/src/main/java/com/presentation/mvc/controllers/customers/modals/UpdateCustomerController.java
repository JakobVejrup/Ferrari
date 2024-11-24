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



// Controller for updating a customer
public class UpdateCustomerController extends ModalController {
    private CustomerModel model;
    private Customer customer;
    private CustomerBaseView view;

    // Konstruktor for UpdateCustomerController
    public UpdateCustomerController (Customer customer) {
        this.customer = customer;
        model = new CustomerModel(customer);

        // Buttons for forskellige scenarier
        Button updateButton = new Button("Opdater Kunde");
        updateButton.setOnAction(this::update);

        Button cancelButton = new Button("Fortryd");
        cancelButton.setOnAction(this::decline);

        // ObservableList for byer
        ObjectProperty<ObservableList<City>> listProperty = new SimpleObjectProperty<>(FXCollections.observableArrayList());
        view = new CustomerBaseView(model, listProperty);
        view.addButtons(updateButton, cancelButton);

        // query for byer med singletons til alle byer
        ServiceSingleton.getInstance().query(new Request(ServiceType.City, CRUDType.ReadAll,
            null,
            (cities) -> {
                ObservableList<City> obs = FXCollections.observableArrayList();
                for(City c : (List<City>)cities)
                    obs.add(c);
                // setter listen af byer til observable list
                listProperty.set(obs);
            })
        );     
}

// Metode til at hente view og returnere
@Override
public Pane getView() {
    return view;
}

// Metode til at opdatere kunde
public void update(ActionEvent event) {
    // query for at opdatere kunde med singletons
    ServiceSingleton.getInstance().query(new Request(ServiceType.Customer, CRUDType.Update,
            model,
            (newCustomer) -> {
            // Hvis kunden ikke er null, så kopier kunden og luk vinduet
                if(newCustomer != null) {
                    customer.copy((Customer)newCustomer);
                    Platform.runLater(this::close);
                }
            },
            // validation for at tjekke om der er fejl i opdateringen
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
// Metode til at afvise opdatering
public void decline(ActionEvent event) {
    close();
}

}
