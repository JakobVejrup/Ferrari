package com.presentation.mvc.views.modals.employee;

import com.model.enums.Occupation;
import com.presentation.mvc.models.modals.employee.CreateEmployeeModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CreateEmployeeView extends VBox {
    public CreateEmployeeView(CreateEmployeeModel model, EventHandler<ActionEvent> buttonAction) {
        TextField email = new TextField();
        model.emailProperty().bindBidirectional(email.textProperty());

        PasswordField password = new PasswordField();
        model.passwordProperty().bindBidirectional(password.textProperty());

        TextField phoneNumber = new TextField();
        model.phoneNumberProperty().bindBidirectional(phoneNumber.textProperty());

        TextField name = new TextField();
        model.nameProperty().bindBidirectional(name.textProperty());

        TextField loanLimit = new TextField();
        //loanLimit.textProperty().bindBidirectional(model.loanLimitProperty(), new NumberStringConverter());
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
        model.occupationProperty().bindBidirectional(occupation.valueProperty());

        Button login = new Button("Lav Bruger");
        login.setOnAction(buttonAction);
        getChildren().addAll(
                new HBox(new Label("Navn:"), name),
                new HBox(new Label("Email:"), email),
                new HBox(new Label("TelefonNummer:"), phoneNumber),
                new HBox(new Label("Password:"), password),
                new HBox(new Label("Maks låne beløb:"), loanLimit),
                new HBox(new Label("Stilling:"), occupation),
                login
        );

    }
}
