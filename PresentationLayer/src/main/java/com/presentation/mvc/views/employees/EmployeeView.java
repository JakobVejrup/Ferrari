package com.presentation.mvc.views.employees;

import com.presentation.mvc.views.table.concretes.EmployeeTable;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import javafx.scene.layout.VBox;

public class EmployeeView extends VBox {

    private TableDecorator table;
    public EmployeeView() {
        table = new EmployeeTable();
    }

}
