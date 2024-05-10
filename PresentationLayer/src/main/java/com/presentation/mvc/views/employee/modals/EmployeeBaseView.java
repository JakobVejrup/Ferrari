package com.presentation.mvc.views.employee.modals;

import com.model.enums.Occupation;
import com.presentation.mvc.models.employees.EmployeeModel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EmployeeBaseView extends VBox {
    public EmployeeBaseView(EmployeeModel model, EventHandler<ActionEvent> accept, EventHandler<ActionEvent> decline, String buttonText) {
        TextField email = new TextField();
        model.emailProperty().bind(email.textProperty());

        TextField phoneNumber = new TextField();
        model.phoneNumberProperty().bind(phoneNumber.textProperty());

        TextField name = new TextField();
        model.nameProperty().bind(name.textProperty());

        TextField loanLimit = new TextField();
        loanLimit.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*"))
                    loanLimit.setText(newValue.replaceAll("[^\\d]", ""));
                if (!loanLimit.getText().isEmpty())
                    model.loanLimitProperty().set(Double.parseDouble(loanLimit.getText()));
            }
        });
        //test if logged in is a manager
        ComboBox<Occupation> occupation = new ComboBox<>(FXCollections.observableArrayList(Occupation.values()));
        model.occupationProperty().bind(occupation.valueProperty());

        Button login = new Button(buttonText);
        login.setOnAction(accept);
        Button exit = new Button("Annuller");
        exit.setOnAction(decline);
        getChildren().addAll(
                new HBox(new Label("Navn:"), name),
                new HBox(new Label("Email:"), email),
                new HBox(new Label("TelefonNummer:"), phoneNumber),
                new HBox(new Label("Maks låne beløb:"), loanLimit),
                new HBox(new Label("Stilling:"), occupation),
                new HBox(login, exit)
        );
    }
}
