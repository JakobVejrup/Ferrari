package com.presentation.mvc.views.employee;

import java.io.ByteArrayInputStream;

import com.model.entities.Employee;
import com.presentation.mvc.views.View;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EmployeeView extends VBox implements View{
    public ObjectProperty<Employee> model;
    public TextField email;
    public TextField phoneNumber;
    public TextField name;
    public ImageView image;

    public EmployeeView(Employee model) {
        this.model = new SimpleObjectProperty<Employee>(model);
        this.model.addListener(new ChangeListener<Employee>() {

            @Override
            public void changed(ObservableValue<? extends Employee> observable, Employee oldValue,
            Employee newValue) {
                name.setText(newValue.getName());
                email.setText(newValue.getEmail());
                phoneNumber.setText(newValue.getPhoneNumber());
                image.setImage(new Image(new ByteArrayInputStream(newValue.getImage())));
            }
            
        });
        email = new TextField(model.getEmail());
        email.setEditable(false);
        phoneNumber = new TextField(model.getPhoneNumber());
        phoneNumber.setEditable(false);

        name = new TextField(model.getName());
        name.setEditable(false);

        image = new ImageView();
        if(model.getImage() != null)
           image.setImage(new Image(new ByteArrayInputStream(model.getImage())));
        getChildren().addAll(
            new HBox(new Label("Sælger navn: "), name ),
            new HBox(new Label("Sælger email: "), email ),
            new HBox(new Label("Sælger tlf: "), phoneNumber ),
            new HBox(new Label("Sælger billede: "), image )
        );
    }

    public void setModel(Employee model) {
        this.model.set(model);
    }

    @Override
    public void addButtons(Button... buttons) {
        getChildren().addFirst(new HBox(buttons));
    }
}
