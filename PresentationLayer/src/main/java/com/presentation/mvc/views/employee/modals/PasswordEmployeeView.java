package com.presentation.mvc.views.employee.modals;

import com.presentation.mvc.models.employees.EmployeeModel;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class PasswordEmployeeView extends EmployeeBaseView {
    public PasswordEmployeeView(EmployeeModel model) {
        super(model);
        PasswordField password = new PasswordField();
        if(model.getPassword() != "")
            password.setText(model.getPassword());
        model.passwordProperty().bind(password.textProperty());
        getChildren().addFirst(new HBox(new Label("Password:"), password));
    }
}
