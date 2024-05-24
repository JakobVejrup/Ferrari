package com.presentation.mvc.views.table.concretes;

import java.sql.Date;
import com.model.entities.Agreement;
import com.model.entities.Customer;
import com.model.entities.Employee;
import com.model.entities.Vehicle;
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
        getColumns().add(fixedTermsCol      = new TableColumn<>("Fixed Terms"));
        getColumns().add(startValueCol      = new TableColumn<RowModel, Number>("Start Value"));
        getColumns().add(startAgreementCol  = new TableColumn<RowModel, Date>("Start Agreement"));
        getColumns().add(RKiCol             = new TableColumn<RowModel, Rating>("RKi"));
        getColumns().add(customerCol        = new TableColumn<RowModel, Customer>("Customer"));
        getColumns().add(employeeCol        = new TableColumn<RowModel, Employee>("Employee"));
        getColumns().add(startCol           = new TableColumn<RowModel, Date>("Start"));
        getColumns().add(endCol             = new TableColumn<RowModel, Date>("End"));
        getColumns().add(endpriceCol        = new TableColumn<RowModel, Number>("End Price"));

        fixedTermsCol.setCellValueFactory((column)          -> new SimpleIntegerProperty(((Agreement) column.getValue().getItem()).getFixedTerms()));
        startValueCol.setCellValueFactory((column)          -> new SimpleDoubleProperty(((Agreement) column.getValue().getItem()).getStartValue()));
        startAgreementCol.setCellValueFactory((column)      -> new SimpleObjectProperty<Date>(((Agreement) column.getValue().getItem()).getStartAgreement()));
        RKiCol.setCellValueFactory((column)                 -> new SimpleObjectProperty<Rating>(((Agreement) column.getValue().getItem()).getRki()));
        customerCol.setCellValueFactory((column)            -> new SimpleObjectProperty<Customer>(((Agreement) column.getValue().getItem()).getCustomer()));
        employeeCol.setCellValueFactory((column)            -> new SimpleObjectProperty<Employee>(((Agreement) column.getValue().getItem()).getEmployee()));
        vehicleCol.setCellValueFactory((column)             -> new SimpleObjectProperty<Vehicle>(((Agreement) column.getValue().getItem()).getVehicle()));
        startCol.setCellValueFactory((column)               -> new SimpleObjectProperty<Date>(((Agreement) column.getValue().getItem()).getStart()));
        endCol.setCellValueFactory((column)                 -> new SimpleObjectProperty<Date>(((Agreement) column.getValue().getItem()).getEnd()));
        endpriceCol.setCellValueFactory((column)            -> new SimpleDoubleProperty(((Agreement) column.getValue().getItem()).getEndPrice()));
    }
    
    @Override
    public GuiTable getTable() {
        return this;
    }
}
