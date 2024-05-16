package com.presentation.mvc.views.table.concretes;

import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.models.vehicle.VehicleModel;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.ui.GuiTable;

import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

public class VehicleTable extends GuiTable implements TableDecorator {
    private TableColumn<RowModel, Integer> idCol;
    private TableColumn<RowModel, String> nameCol;
    private TableColumn<RowModel, Double> priceCol;

    public VehicleTable() {
        getColumns().add(idCol = new TableColumn<RowModel, Integer>("ID"));
        getColumns().add(nameCol = new TableColumn<RowModel, String>("Navn"));
        getColumns().add(priceCol = new TableColumn<RowModel, Double>("Pris"));
        nameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel,String>,ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RowModel, String> column) {
                return ((VehicleModel)column.getValue().getItem()).nameProperty();
            }
        });
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        idCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<RowModel, Integer> column) {
                return ((VehicleModel)column.getValue().getItem()).vehicleIntegerProperty();
            }
        });
        idCol.setCellFactory(TextFieldTableCell.forTableColumn());
        priceCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<RowModel, Double> column) {
                return ((VehicleModel)column.getValue().getItem()).priceDoubleProperty();
            }
        });
        priceCol.setCellFactory(new Callback<TableColumn.CellDataFeatures<RowModel, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<RowModel, Double> column) {
                return ((VehicleModel)column.getValue().getItem()).priceDoubleProperty();
            }
        });
    }
    @Override
    public GuiTable getTable() {
        return this;
    }
}

// Selecet bil update og slet