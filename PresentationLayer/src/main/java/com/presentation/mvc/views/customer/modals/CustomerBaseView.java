package com.presentation.mvc.views.customer.modals;

import com.presentation.mvc.models.customer.CustomerModel;
import com.presentation.mvc.views.View;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class CustomerBaseView extends VBox implements View{
    public CustomerBaseView(CustomerModel model) {
        TextField email = new TextField(model.getEmail());
        model.emailProperty().bind(email.textProperty());

        TextField phoneNumber = new TextField(model.getPhoneNumber());
        model.phoneNumberProperty().bind(phoneNumber.textProperty());

        TextField name = new TextField(model.getName());
        model.nameProperty().bind(name.textProperty());

        TextField address = new TextField(model.getAddress());
        model.addressProperty().bind(address.textProperty());

        TextField cityZip = new TextField(model.getCityZip());
        model.CityZipProperty().bind(cityZip.textProperty());

        TextField cpr = new TextField(model.getCpr());
        model.CprProperty().bind(cpr.textProperty());
    }

    @Override
    public void addButtons(Button... buttons) {
        getChildren().add(new HBox(buttons));
    }
}