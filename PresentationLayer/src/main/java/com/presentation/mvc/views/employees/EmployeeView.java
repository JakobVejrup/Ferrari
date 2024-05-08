package com.presentation.mvc.views.employees;

import com.presentation.mvc.views.table.concretes.EmployeeTable;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.ui.GuiTable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class EmployeeView extends VBox {
    private Button newUser;
    public EmployeeView(EventHandler<ActionEvent> newUserAction) {
        getStyleClass().add("employees");
        newUser = new Button("Lav ny bruger");
        newUser.setOnAction(newUserAction);
        getChildren().add(newUser);
    }
    public void setTable(GuiTable table) {
        table.setup(this);
    }

}
