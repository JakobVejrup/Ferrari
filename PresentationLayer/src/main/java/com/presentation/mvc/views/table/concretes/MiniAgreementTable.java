package com.presentation.mvc.views.table.concretes;
import java.sql.Date;
import com.presentation.mvc.models.agreements.AgreementModel;
import com.model.entities.Customer;
import com.model.entities.Employee;
import com.model.entities.Vehicle;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.ui.GuiTable;
import com.rki.rki.Rating;

import javafx.scene.control.TableColumn;

public class MiniAgreementTable extends GuiTable implements TableDecorator {
private TableColumn<RowModel, Customer> customerCol;
private TableColumn<RowModel, Employee> employeeCol;
private TableColumn<RowModel, Vehicle> vehicleCol;

public MiniAgreementTable() {
    getColumns().add(customerCol        = new TableColumn<RowModel, Customer>("Customer"));
    getColumns().add(employeeCol        = new TableColumn<RowModel, Employee>("Employee"));
    getColumns().add(vehicleCol         = new TableColumn<RowModel, Vehicle>("Vehicle"));

    customerCol.setCellValueFactory((column)        -> ((AgreementModel)column.getValue().getItem()).customerProperty());
    employeeCol.setCellValueFactory((column)        -> ((AgreementModel)column.getValue().getItem()).employeeProperty());
    vehicleCol.setCellValueFactory((column)         -> ((AgreementModel)column.getValue().getItem()).vehicleProperty());

}
@Override
public GuiTable getTable() {
    return this;
    }
}

