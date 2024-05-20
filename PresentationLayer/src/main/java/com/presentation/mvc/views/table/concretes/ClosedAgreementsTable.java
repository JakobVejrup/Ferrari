package com.presentation.mvc.views.table.concretes;

import java.sql.Date;
import com.presentation.mvc.models.agreements.ClosedAgreementsModel;
import com.model.entities.Customer;
import com.model.entities.Employee;
import com.model.entities.Vehicle;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.ui.GuiTable;
import com.rki.rki.Rating;

import javafx.scene.control.TableColumn;

public class ClosedAgreementsTable extends GuiTable implements TableDecorator{
    private TableColumn<RowModel, Number> fixedTermsCol;
    private TableColumn<RowModel, Number> startValueCol;
    private TableColumn<RowModel, Date> startAgreementCol;
    private TableColumn<RowModel, Rating> RKiCol;
    private TableColumn<RowModel, Customer> customerCol;
    private TableColumn<RowModel, Employee> employeeCol;
    private TableColumn<RowModel, Vehicle> vehicleCol;
    private TableColumn<RowModel, Date> startCol;
    private TableColumn<RowModel, Date> endCol;
    private TableColumn<RowModel, Number> endpriceCol;
   

    public ClosedAgreementsTable(){
        getColumns().add(fixedTermsCol      = new TableColumn<RowModel, Number>("Fixed Terms"));
        getColumns().add(startValueCol      = new TableColumn<RowModel, Number>("Start Value"));
        getColumns().add(startAgreementCol  = new TableColumn<RowModel, Date>("Start Agreement"));
        getColumns().add(RKiCol             = new TableColumn<RowModel, Rating>("RKi"));
        getColumns().add(customerCol        = new TableColumn<RowModel, Customer>("Customer"));
        getColumns().add(employeeCol        = new TableColumn<RowModel, Employee>("Employee"));
        getColumns().add(startCol           = new TableColumn<RowModel, Date>("Start"));
        getColumns().add(endCol             = new TableColumn<RowModel, Date>("End"));
        getColumns().add(endpriceCol        = new TableColumn<RowModel, Number>("End Price"));

        fixedTermsCol.setCellValueFactory((column)          -> ((ClosedAgreementsModel) column.getValue().getItem()).fixedTermsProperty());
        startValueCol.setCellValueFactory((column)          -> ((ClosedAgreementsModel) column.getValue().getItem()).startValueProperty());
        startAgreementCol.setCellValueFactory((column)      -> ((ClosedAgreementsModel) column.getValue().getItem()).startAgreementProperty());
        RKiCol.setCellValueFactory((column)                 -> ((ClosedAgreementsModel) column.getValue().getItem()).RkiProperty());
        customerCol.setCellValueFactory((column)            -> ((ClosedAgreementsModel) column.getValue().getItem()).customerProperty());
        employeeCol.setCellValueFactory((column)            -> ((ClosedAgreementsModel) column.getValue().getItem()).employeeProperty());
        vehicleCol.setCellValueFactory((column)             -> ((ClosedAgreementsModel) column.getValue().getItem()).vehicleProperty());
        startCol.setCellValueFactory((column)               -> ((ClosedAgreementsModel) column.getValue().getItem()).startProperty());
        endCol.setCellValueFactory((column)                 -> ((ClosedAgreementsModel) column.getValue().getItem()).endProperty());
        endpriceCol.setCellValueFactory((column)            -> ((ClosedAgreementsModel) column.getValue().getItem()).endPriceProperty());
    }

    @Override
    public GuiTable getTable() {
        return this;
    }
}
