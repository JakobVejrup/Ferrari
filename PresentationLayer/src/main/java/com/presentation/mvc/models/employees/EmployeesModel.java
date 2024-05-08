package com.presentation.mvc.models.employees;

import com.logic.services.enums.ServiceType;
import com.model.entities.Employee;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.models.table.TableModel;
import javafx.collections.ObservableList;

public class EmployeesModel {
    private TableModel tableModel;
    public EmployeesModel(TableModel tableModel) {
        this.tableModel = tableModel;
    }
    public ObservableList<RowModel> getEmployeeRows() {
        return tableModel.getRows();
    }
}
