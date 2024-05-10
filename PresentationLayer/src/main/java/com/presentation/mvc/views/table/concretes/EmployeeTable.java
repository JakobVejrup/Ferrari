package com.presentation.mvc.views.table.concretes;

import com.model.entities.Employee;
import com.model.enums.Occupation;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.ui.GuiTable;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

public class EmployeeTable extends GuiTable implements TableDecorator {
    private TableColumn<RowModel, String> nameCol;
    private TableColumn<RowModel, String> phoneCol;
    private TableColumn<RowModel, String> emailCol;
    private TableColumn<RowModel, Occupation> occupationCol;
    private TableColumn<RowModel, Double> loanLimitCol;

    public EmployeeTable() {
        getColumns().add(nameCol = new TableColumn<RowModel, String>("Navn"));
        getColumns().add(phoneCol = new TableColumn<RowModel, String>("Tlf Nr"));
        getColumns().add(emailCol = new TableColumn<RowModel, String>("Email"));
        getColumns().add(occupationCol = new TableColumn<RowModel, Occupation>("Stilling"));
        getColumns().add(loanLimitCol = new TableColumn<RowModel, Double>("Maks Lån"));
        nameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RowModel, String> column) {
                return new SimpleObjectProperty<String>(((Employee)column.getValue().getItem()).getName());
            }
        });
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RowModel, String> column) {
                return new SimpleObjectProperty<String>(((Employee)column.getValue().getItem()).getPhoneNumber());
            }
        });
        phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RowModel, String> column) {
                return new SimpleObjectProperty<String>(((Employee)column.getValue().getItem()).getPhoneNumber());
            }
        });
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        occupationCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel, Occupation>, ObservableValue<Occupation>>() {
            @Override
            public ObservableValue<Occupation> call(TableColumn.CellDataFeatures<RowModel, Occupation> column) {
                return new SimpleObjectProperty<Occupation>(((Employee)column.getValue().getItem()).getOccupation());
            }
        });
        loanLimitCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<RowModel, Double> column) {
                return new SimpleObjectProperty<Double>(((Employee)column.getValue().getItem()).getLoanLimit());
            }
        });
    }

    @Override
    public GuiTable getTable() {
        return this;
    }
}
