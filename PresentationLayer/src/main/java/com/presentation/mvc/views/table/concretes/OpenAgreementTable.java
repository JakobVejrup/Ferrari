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

public class OpenAgreementTable extends GuiTable implements TableDecorator {
private TableColumn<RowModel, Number> fixedTermsCol;
private TableColumn<RowModel, Number> startValueCol;
private TableColumn<RowModel, Date> startAgreementCol;
private TableColumn<RowModel, Rating> RKiCol;
private TableColumn<RowModel, Customer> customerCol;
private TableColumn<RowModel, Employee> employeeCol;
private TableColumn<RowModel, Vehicle> vehicleCol;

public OpenAgreementTable() {
    getColumns().add(fixedTermsCol      = new TableColumn<RowModel, Number>("Fixed Terms"));
    getColumns().add(startValueCol      = new TableColumn<RowModel, Number>("Start Value"));
    getColumns().add(startAgreementCol  = new TableColumn<RowModel, Date>("Start Agreement"));
    getColumns().add(RKiCol             = new TableColumn<RowModel, Rating>("RKi"));
    getColumns().add(customerCol        = new TableColumn<RowModel, Customer>("Customer"));
    getColumns().add(employeeCol        = new TableColumn<RowModel, Employee>("Employee"));
    getColumns().add(vehicleCol         = new TableColumn<RowModel, Vehicle>("Vehicle"));

    fixedTermsCol.setCellValueFactory((column)      -> ((AgreementModel)column.getValue().getItem()).fixedTermsProperty());
    startValueCol.setCellValueFactory((column)      -> ((AgreementModel)column.getValue().getItem()).startValueProperty());
    startAgreementCol.setCellValueFactory((column)  -> ((AgreementModel)column.getValue().getItem()).startAgreementProperty());
    RKiCol.setCellValueFactory((column)             -> ((AgreementModel)column.getValue().getItem()).RKiProperty());
    customerCol.setCellValueFactory((column)        -> ((AgreementModel)column.getValue().getItem()).customerProperty());
    employeeCol.setCellValueFactory((column)        -> ((AgreementModel)column.getValue().getItem()).employeeProperty());
    vehicleCol.setCellValueFactory((column)         -> ((AgreementModel)column.getValue().getItem()).vehicleProperty());

        }
@Override
public GuiTable getTable() {
    return this;
    }
}

