package com.presentation.mvc.views.customer;

import java.io.ByteArrayInputStream;

import com.model.entities.Customer;
import com.model.entities.Employee;
import com.presentation.mvc.views.View;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SingleCustomerView extends VBox implements View{
    public Customer model;
    public TextField email;
    public TextField phoneNumber;
    public TextField name;
    public TextField cpr;

    public SingleCustomerView(Customer model) {
        this.model = model;
        email = new TextField(model.getEmail());
        email.setEditable(false);
        phoneNumber = new TextField(model.getPhoneNumber());
        phoneNumber.setEditable(false);

        name = new TextField(model.getName());
        name.setEditable(false);

        cpr = new TextField(model.getCpr());
        cpr.setEditable(false);
        getChildren().addAll(
            new HBox(new Label("Kunde navn: "), name ),
            new HBox(new Label("Kunde email: "), email ),
            new HBox(new Label("Kunde tlf: "), phoneNumber ),
            new HBox(new Label("Kunde cpr: "), cpr )
        );
        setAlignment(Pos.CENTER);
    }

    public void setModel(Customer model) {
        this.model = model;
        name.setText(model.getName());
        email.setText(model.getEmail());
        phoneNumber.setText(model.getPhoneNumber());
        cpr.setText(model.getCpr());
    }

    @Override
    public void addButtons(Button... buttons) {
        getChildren().addFirst(new HBox(buttons));
    }
    
}
