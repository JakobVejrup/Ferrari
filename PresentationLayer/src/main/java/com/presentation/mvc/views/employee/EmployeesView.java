package com.presentation.mvc.views.employee;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class EmployeesView extends VBox {
    private Button newUser;
    public EmployeesView(EventHandler<ActionEvent> newUserAction) {
        getStyleClass().add("employees");
        newUser = new Button("Lav ny bruger");
        newUser.setOnAction(newUserAction);
        getChildren().add(newUser);
    }

}
