package com.presentation.mvc.views.table.concretes;

import com.presentation.mvc.controllers.table.factories.ImageFactory;
import com.presentation.mvc.controllers.vehicle.AllVehiclesController;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.models.vehicle.VehicleModel;
import com.presentation.mvc.views.table.decorators.ImageTableDecorator;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.ui.GuiTable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

public class VehicleTable extends GuiTable implements TableDecorator {
    private TableColumn<RowModel, String> name;
    private TableColumn<RowModel, Double> price;
    private TableColumn<RowModel, Number> id;
    private TableView<VehicleModel> tableView;
    private VehicleModel viewModel;

    public VehicleTable() {
        tableView = new TableView<>();
    
    
        TableColumn<VehicleModel, String> nameCol = new TableColumn<>("BilNavn");
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<VehicleModel, Number> priceCol = new TableColumn<>("Pris");
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        
        ImageTableDecorator image = new ImageTableDecorator(this, new ImageFactory("Bil"));

        tableView.getColumns().addAll(nameCol, priceCol);
        tableView.setItems(viewModel.getVehicleModels());
    }

    @Override
    public GuiTable getTable() {
        return this;
    }
    
    public TableView<VehicleModel> getTableview() {
        return tableView;
    }
}
