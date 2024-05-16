package com.presentation.mvc.views.table.concretes;

import com.model.enums.Occupation;
import com.presentation.mvc.models.employees.EmployeeModel;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.ui.GuiTable;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

public class EmployeeTable extends GuiTable implements TableDecorator {
    private TableColumn<RowModel, String> nameCol;
    private TableColumn<RowModel, String> phoneCol;
    private TableColumn<RowModel, String> emailCol;
    private TableColumn<RowModel, Occupation> occupationCol;
    private TableColumn<RowModel, Number> loanLimitCol;

    public EmployeeTable() {
        getColumns().add(nameCol = new TableColumn<RowModel, String>("Navn"));
        getColumns().add(phoneCol = new TableColumn<RowModel, String>("Tlf Nr"));
        getColumns().add(emailCol = new TableColumn<RowModel, String>("Email"));
        getColumns().add(occupationCol = new TableColumn<RowModel, Occupation>("Stilling"));
        getColumns().add(loanLimitCol = new TableColumn<RowModel, Number>("Maks Lån"));
        nameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RowModel, String> column) {
                return ((EmployeeModel)column.getValue().getItem()).nameProperty();
            }
        });
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RowModel, String> column) {
                return ((EmployeeModel)column.getValue().getItem()).phoneNumberProperty();
            }
        });
        phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RowModel, String> column) {
                return ((EmployeeModel)column.getValue().getItem()).emailProperty();
            }
        });
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        occupationCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel, Occupation>, ObservableValue<Occupation>>() {
            @Override
            public ObservableValue<Occupation> call(TableColumn.CellDataFeatures<RowModel, Occupation> column) {
                return ((EmployeeModel)column.getValue().getItem()).occupationProperty();
            }
        });
        loanLimitCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<RowModel, Number> column) {
                return ((EmployeeModel)column.getValue().getItem()).loanLimitProperty();
            }
        });
    }

    @Override
    public GuiTable getTable() {
        return this;
    }
}
