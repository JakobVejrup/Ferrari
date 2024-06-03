package com.presentation.mvc.views.table.concretes;

import com.presentation.mvc.controllers.table.factories.ImageFactory;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.models.vehicle.VehicleModel;
import com.presentation.mvc.views.table.decorators.ImageTableDecorator;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.ui.GuiTable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
//jakob
public class VehicleTable extends GuiTable implements TableDecorator {
    private TableColumn<RowModel, String> nameCol;
    private TableColumn<RowModel, Double> priceCol;

    public VehicleTable() {
        ImageTableDecorator image = new ImageTableDecorator(this, new ImageFactory("vehicle"));
    
        TableColumn<RowModel, String> nameCol = new TableColumn<>("BilNavn");
        nameCol.setCellValueFactory(cellData -> ((VehicleModel)cellData.getValue().getItem()).nameProperty());
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<RowModel, Number> priceCol = new TableColumn<>("Pris");
        priceCol.setCellValueFactory(cellData -> ((VehicleModel)cellData.getValue().getItem()).priceProperty());
        getColumns().addAll(nameCol, priceCol);
    }

    @Override
    public GuiTable getTable() {
        return this;
    }
    
}
