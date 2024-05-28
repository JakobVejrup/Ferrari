package com.presentation.mvc.views.table.concretes;

import java.sql.Date;
import com.model.entities.Agreement;
import com.model.entities.Customer;
import com.model.entities.Employee;
import com.model.entities.Vehicle;
import com.presentation.mvc.controllers.table.CurrencyCell;
import com.presentation.mvc.models.agreements.AgreementModel;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.ui.GuiTable;
import com.rki.rki.Rating;

import javafx.beans.binding.Bindings;
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

        fixedTermsCol.setCellValueFactory((cellData)          -> ((AgreementModel) cellData.getValue().getItem()).fixedTermsProperty());
        startValueCol.setCellValueFactory((cellData)          -> ((AgreementModel) cellData.getValue().getItem()).startValueProperty());
        startValueCol.setCellFactory(CurrencyCell.forTableColumn());
        startAgreementCol.setCellValueFactory((cellData)      -> ((AgreementModel) cellData.getValue().getItem()).startAgreementProperty());
        RKiCol.setCellValueFactory((cellData)                 -> ((AgreementModel) cellData.getValue().getItem()).RKiProperty());
        customerCol.setCellValueFactory((cellData)            -> ((AgreementModel) cellData.getValue().getItem()).customerProperty());
        employeeCol.setCellValueFactory((cellData)            -> ((AgreementModel) cellData.getValue().getItem()).employeeProperty());
        vehicleCol.setCellValueFactory((cellData)             -> ((AgreementModel) cellData.getValue().getItem()).vehicleProperty());
        startCol.setCellValueFactory((cellData)               -> ((AgreementModel) cellData.getValue().getItem()).startProperty());
        endCol.setCellValueFactory((cellData)                 -> ((AgreementModel) cellData.getValue().getItem()).endProperty());
        endpriceCol.setCellValueFactory((cellData)            -> ((AgreementModel) cellData.getValue().getItem()).endPriceProperty());
        endpriceCol.setCellFactory(CurrencyCell.forTableColumn());
    }
    
    @Override
    public GuiTable getTable() {
        return this;
    }
}
