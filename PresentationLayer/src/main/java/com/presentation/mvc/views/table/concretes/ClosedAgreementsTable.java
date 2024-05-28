package com.presentation.mvc.views.table.concretes;

import java.sql.Date;
import com.model.entities.Agreement;
import com.model.entities.Customer;
import com.model.entities.Employee;
import com.model.entities.Vehicle;
import com.presentation.mvc.models.agreements.AgreementModel;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.ui.GuiTable;
import com.rki.rki.Rating;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
        getColumns().add(fixedTermsCol      = new TableColumn<>("Terminer"));
        getColumns().add(startValueCol      = new TableColumn<RowModel, Number>("Indskud"));
        getColumns().add(startAgreementCol  = new TableColumn<RowModel, Date>("Tilbud Givet den"));
        getColumns().add(RKiCol             = new TableColumn<RowModel, Rating>("Købers Rki"));
        getColumns().add(customerCol        = new TableColumn<RowModel, Customer>("Køber"));
        getColumns().add(employeeCol        = new TableColumn<RowModel, Employee>("Sælger"));
        getColumns().add(vehicleCol         = new TableColumn<RowModel, Vehicle>("Køretøj"));
        getColumns().add(startCol           = new TableColumn<RowModel, Date>("I kraft fra"));
        getColumns().add(endCol             = new TableColumn<RowModel, Date>("I Kraft til"));
        getColumns().add(endpriceCol        = new TableColumn<RowModel, Number>("Total pris"));

        fixedTermsCol.setCellValueFactory((column)          -> ((AgreementModel) column.getValue().getItem()).fixedTermsProperty());
        startValueCol.setCellValueFactory((column)          -> ((AgreementModel) column.getValue().getItem()).startValueProperty());
        startAgreementCol.setCellValueFactory((column)      -> ((AgreementModel) column.getValue().getItem()).startAgreementProperty());
        RKiCol.setCellValueFactory((column)                 -> ((AgreementModel) column.getValue().getItem()).RKiProperty());
        customerCol.setCellValueFactory((column)            -> ((AgreementModel) column.getValue().getItem()).customerProperty());
        employeeCol.setCellValueFactory((column)            -> ((AgreementModel) column.getValue().getItem()).employeeProperty());
        vehicleCol.setCellValueFactory((column)             -> ((AgreementModel) column.getValue().getItem()).vehicleProperty());
        startCol.setCellValueFactory((column)               -> ((AgreementModel) column.getValue().getItem()).startProperty());
        endCol.setCellValueFactory((column)                 -> ((AgreementModel) column.getValue().getItem()).endProperty());
        endpriceCol.setCellValueFactory((column)            -> ((AgreementModel) column.getValue().getItem()).endPriceProperty());
    }
    
    @Override
    public GuiTable getTable() {
        return this;
    }
}
