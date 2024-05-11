package com.presentation.mvc.views.employee.modals;

import com.presentation.mvc.models.employees.EmployeeModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class CreateEmployeeView extends EmployeeBaseView {
    public CreateEmployeeView(EmployeeModel model) {
        super(model);
        PasswordField password = new PasswordField();
        model.passwordProperty().bind(password.textProperty());
        getChildren().addFirst(new HBox(new Label("Password:"), password));
    }
}
