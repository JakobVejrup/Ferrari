package com.presentation.mvc.views.vehicle;

import com.model.entities.Vehicle;
import com.presentation.mvc.controllers.vehicle.VehicleController;
import com.presentation.mvc.models.vehicle.VehicleModel;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;



public class VehicleView {
    private VehicleController viewModel;
    private TableView<VehicleModel> tableView;

    public VehicleView(VehicleController viewModel) {
        this.viewModel = viewModel;
        tableView = new TableView<>();

        // ID column
        TableColumn<VehicleModel, Number> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(cellData -> cellData.getValue().vehicleIdProperty());
        idCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter())); 
            
        // Name column
        TableColumn<VehicleModel, String> nameCol = new TableColumn<>("Navn");
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        // Price column
        TableColumn<VehicleModel, Number> priceCol = new TableColumn<>("Pris");
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        priceCol.setCellFactory(TextFieldTableCell.forTableColumn());

        // Add columns to the table view
        tableView.getColumns().addAll(idCol, nameCol, priceCol);
        tableView.setItems(viewModel.getVehicleModels());
    }

    public TableView<VehicleModel> getTableView() {
        return tableView;
    }
}

