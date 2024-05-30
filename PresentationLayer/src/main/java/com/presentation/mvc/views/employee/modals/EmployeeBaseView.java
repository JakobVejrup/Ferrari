package com.presentation.mvc.views.employee.modals;

import com.model.enums.Occupation;
import com.presentation.mvc.models.employees.EmployeeModel;
import com.presentation.mvc.views.View;
import com.presentation.mvc.views.generalgui.NiceHBox;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public class EmployeeBaseView extends VBox implements View{
    public EmployeeBaseView(EmployeeModel model) {
        TextField email = new TextField(model.getEmail());
        model.emailProperty().bind(email.textProperty());

        TextField phoneNumber = new TextField(model.getPhoneNumber());
        model.phoneNumberProperty().bind(phoneNumber.textProperty());

        TextField name = new TextField(model.getName());
        model.nameProperty().bind(name.textProperty());

        TextField loanLimit = new TextField(String.valueOf(model.getLoanLimit()));
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
        occupation.setValue(model.getOccupation());
        model.occupationProperty().bind(occupation.valueProperty());

        getChildren().addAll(
                new NiceHBox("rightContainer", new Insets(5), new Label("Navn:"), name),
                new NiceHBox("rightContainer", new Insets(5), new Label("Email:"), email),
                new NiceHBox("rightContainer", new Insets(5), new Label("TelefonNummer:"), phoneNumber),
                new NiceHBox("rightContainer", new Insets(5), new Label("Maks låne beløb:"), loanLimit),
                new NiceHBox("rightContainer", new Insets(5), new Label("Stilling:"), occupation)
        );
    }

    @Override
    public void addButtons(Button... buttons) {
        getChildren().add(new HBox(buttons));
    }
}
