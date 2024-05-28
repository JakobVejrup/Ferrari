package com.presentation.mvc.views.table.concretes;

import java.sql.Date;

import com.presentation.mvc.controllers.table.CurrencyCell;
import com.presentation.mvc.models.Invoice.InvoiceModel;
import com.presentation.mvc.models.employees.EmployeeModel;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.ui.GuiTable;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class InvoiceTable extends GuiTable implements TableDecorator {
    private TableColumn<RowModel, Number> numberCol;
    private TableColumn<RowModel, Date> dateStartCol;
    private TableColumn<RowModel, Date> dateEndCol;
    private TableColumn<RowModel, Number> plusCol;
    private TableColumn<RowModel, Number> payedCol;
    private TableColumn<RowModel, Number> minusCol;
    private TableColumn<RowModel, Number> ultimoCol;
    private TableColumn<RowModel, Number> primoCol;
    private TableColumn<RowModel, String> detailsCol;

    public InvoiceTable() {
        getColumns().add(numberCol = new TableColumn<>("Nr"));
        getColumns().add(dateStartCol = new TableColumn<>("Fra"));
        getColumns().add(dateEndCol = new TableColumn<>("Til"));
        getColumns().add(payedCol = new TableColumn<>("Ydelse"));
        getColumns().add(plusCol = new TableColumn<>("Afdrag"));
        getColumns().add(minusCol = new TableColumn<>("Renter"));
        getColumns().add(primoCol = new TableColumn<>("Før betaling"));
        getColumns().add(ultimoCol = new TableColumn<>("Efter betaling"));
        getColumns().add(detailsCol = new TableColumn<>("Oplysninger"));

        // can be done with lambdas as well since callback is a single method interface, a functional interface, CallBack<P,R> with method signature R call(P p).
        numberCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<RowModel, Number> row) {
                return ((InvoiceModel)row.getValue().getItem()).numberProperty();
            }
        });
        dateStartCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<RowModel, Date> row) {
                return ((InvoiceModel)row.getValue().getItem()).dateStartProperty();
            }
        });
        detailsCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RowModel, String> row) {
                return ((InvoiceModel)row.getValue().getItem()).detailsProperty();
            }
        });
        payedCol.setCellValueFactory((row) -> ((InvoiceModel) row.getValue().getItem()).payedProperty());
        payedCol.setCellFactory(CurrencyCell.forTableColumn());
        dateEndCol.setCellValueFactory( (row) -> ((InvoiceModel)row.getValue().getItem()).dateEndProperty());
        plusCol.setCellValueFactory((row) -> ((InvoiceModel) row.getValue().getItem()).plusProperty());
        plusCol.setCellFactory(CurrencyCell.forTableColumn());
        minusCol.setCellValueFactory((row) -> ((InvoiceModel) row.getValue().getItem()).minusProperty());
        minusCol.setCellFactory(CurrencyCell.forTableColumn());
        ultimoCol.setCellValueFactory((row) -> ((InvoiceModel) row.getValue().getItem()).ultimoProperty());
        ultimoCol.setCellFactory(CurrencyCell.forTableColumn());
        primoCol.setCellValueFactory((row) -> ((InvoiceModel) row.getValue().getItem()).primoProperty());
        primoCol.setCellFactory(CurrencyCell.forTableColumn());

    }
    @Override
    public GuiTable getTable() {
        return this;
    }
}
