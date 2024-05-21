package com.presentation.mvc.views.employee;

import com.presentation.mvc.views.table.ui.GuiTable;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class EmployeesView extends VBox {
    private Button newUser;
    public EmployeesView(Button... buttons) {
        getStyleClass().add("employees");
        getChildren().addAll(buttons);
    }

}
